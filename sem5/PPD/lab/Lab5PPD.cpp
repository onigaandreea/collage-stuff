// Lab5PPD.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <filesystem>
#include <fstream>
#include <string>
#include <set>
#include <map>
#include <chrono>
#include <thread>
#include <mutex>
#include <atomic>
#include <Windows.h>
#include <functional>

using namespace std;

struct Participant {
    int id;
    int score;
    int country;
};

class ParticipantsList {
private:
    struct Node {
        Participant p;
        Node* next;
        mutex mtx;

        Node(const Participant& p, Node* next = nullptr) : p(p), next(next), mtx() {}
    };
    
    Node* lock_node(Node* node) {
        if (node)
            node->mtx.lock();
        return node;
    }

    void unlock_node(Node* node) {
        if (node)
            node->mtx.unlock();
    }

    template<typename... Args>
    void unlock_node(Node* node, Args... args) {
        unlock_node(node);
        unlock_node(args...);
    }

    Node* start;
    Node* end;

public:
    ParticipantsList() {
        end = new Node(Participant());
        start = new Node(Participant(), end);
    }

    virtual bool is_sorted(const Participant& p, const Participant& q) = 0;

    bool find(function<bool(const Participant&)> pred) {
        Node* node1 = lock_node(start);
        Node* node2 = lock_node(node1->next);

        if (node2 == end) {
            unlock_node(node1, node2);
            return false;
        }

        Node* node3 = lock_node(node2->next);

        while (node3 != nullptr) {
            if (pred(node2->p)) {
                unlock_node(node1, node2, node3);
                return true;
            }

            unlock_node(node1);
            node1 = node2;
            node2 = node3;
            node3 = lock_node(node3->next);
        }

        unlock_node(node1, node2, node3);
        return false;
    }

    bool find_and_delete(function<bool(const Participant&)> pred, Participant& result)
    {
        Node* node1 = lock_node(start);
        Node* node2 = lock_node(node1->next);

        if (node2 == end) // empty list
        {
            unlock_node(node1, node2);
            return false;
        }

        Node* node3 = lock_node(node2->next);

        while (node3 != nullptr)
        {
            if (pred(node2->p))
            {
                result = node2->p;
                node1->next = node3;
                unlock_node(node1, node2, node3);
                delete node2;
                return true;
            }

            unlock_node(node1);
            node1 = node2;
            node2 = node3;
            node3 = lock_node(node3->next);
        }

        unlock_node(node1, node2, node3);

        return false;
    }

    void add(const Participant& p) {
        Node* node = new Node(p);

        Node* node1 = lock_node(start);
        Node* node2 = lock_node(node1->next);

        if (node2 == end) {
            node1->next = node;
            node->next = node2;
            unlock_node(node1, node2);
            return;
        }

        if (is_sorted(node->p, node2->p)) {
            node1->next = node;
            node->next = node2;
            unlock_node(node1, node2);
            return;
        }

        unlock_node(node1), node1 = node2, node2 = lock_node(node1->next);

        while (node2 != end) {
            if (is_sorted(node1->p, node->p) && is_sorted(node->p, node2->p)) {
                node1->next = node;
                node->next = node2;
                unlock_node(node1, node2);
                return;
            }
            unlock_node(node1), node1 = node2, node2 = lock_node(node1->next);
        }

        node1->next = node;
        node->next = node2;
        unlock_node(node1, node2);
    }

    ~ParticipantsList() {
        for (Node* n = start, *tmp; n != nullptr;) {
            tmp = n;
            n = n->next;
            delete tmp;
        }
    }

    void iterate_sync(function<void(const Participant&)> callback) const {
        for (Node* n = start->next; n != end; n = n->next) {
            callback(n->p);
        }
    }
};


class RankingList : ParticipantsList {
private:
    set<int> disqualified;
    map<int, mutex> id_mutex;
    mutex mtx;

public:
    virtual bool is_sorted(const Participant& p, const Participant& q) override {
        return p.score > q.score || (p.score == q.score && p.id <= q.id);
    }

    void update_or_add(const Participant& p) {
        mtx.lock();
        lock_guard<mutex> lk(id_mutex[p.id]);
        mtx.unlock();

        if (disqualified.find(p.id) != disqualified.end())
            return;

        Participant tmp = { p.id, 0, p.country };
        find_and_delete([&p](const Participant& r) { return r.id == p.id; }, tmp);

        if (p.score < 0) {
            disqualified.insert(p.id);
            return;
        }
        else {
            tmp.score += p.score;
            add(tmp);
        }
    }

    friend ostream& operator << (ostream& o, const RankingList& l)
    {
        l.iterate_sync([&o](const Participant& r) { o << r.id << " " << r.score << " " << r.country << "\n"; });
        o << "Descalificati:\n";
        for (int x : l.disqualified)
            o << x << "\n";
        return o;
    }

};


class Queue {
private:
    int capacity;

    Participant* participants;
    int start = 0, end = 0, size = 0;

    bool is_active = true;
    mutable mutex mtx;
    condition_variable var_empty;
    condition_variable var_full;

    atomic<int> dequeueing = 0;

public:
    Queue(int capacity = 50) : capacity(capacity) {
        participants = new Participant[capacity]();
    }

    void enqueue(const Participant& p) {
        unique_lock<mutex> lock(mtx);

        while (size == capacity) {
            var_full.wait(lock);
        }

        participants[end] = p;
        end = (end + 1) % capacity;
        size++;

        var_empty.notify_one();
    }

    bool dequeue(Participant& p) {
        unique_lock<mutex> lock(mtx);
        dequeueing++;

        while (is_active && size == 0) {
            var_empty.wait(lock);
        }

        dequeueing--;

        if (!is_active)
            if (size == 0)
                return false;

        p = participants[start];
        start = (start + 1) % capacity;
        size--;

        var_full.notify_one();

        return true;
    }

    void shutdown() {
        unique_lock<mutex> lock(mtx);
        is_active = false;
        lock.unlock();

        while (dequeueing.load() != 0) {
            lock.lock();
            var_empty.notify_all();
            lock.unlock();
        }
    }

    ~Queue() {
        delete[] participants;
    }
};

void producer(RankingList* r_list, Queue* queue, int pr, int pid)
{
    int q = 50 / pr, r = 50 % pr;
    int start = pid * q + (r <= pid ? r : pid);
    int end = (pid + 1) * q + (r <= (pid + 1) ? r : pid + 1);
    
    for (int k = start; k < end; k++)
    {
        int country = k / 10 + 1, part = k % 10 + 1;
        ifstream f("data\\C" + to_string(country) + "_P" + to_string(part) + ".txt");

        for (int part, score, k = 0; k < 50000 && (f >> part >> score); k++)
        {
            Participant p = { part, score, country };
            queue->enqueue(p);
        }
        f.close();

    }
}

void consumer(RankingList* r_list, Queue* queue, int pid)
{
    while (true) {
        Participant p;
        if (!queue->dequeue(p))
            break;
        r_list->update_or_add(p);
    }
}

void parallel(int pr, int pw)
{
    RankingList r_list;
    Queue queue;

    thread* consumers = new thread[pw]{};
    for (int p = 0; p < pw; p++) consumers[p] = thread(consumer, &r_list, &queue, p);


    thread* producers = new thread[pr]{};
    for (int p = 0; p < pr; p++) producers[p] = thread(producer, &r_list, &queue, pr, p);

    for (int p = 0; p < pr; p++) producers[p].join();

    delete[] producers;

    queue.shutdown();

    for (int p = 0; p < pw; p++) consumers[p].join();

    ofstream g("result.txt");
    g << r_list;
    g.close();
}

int main()
{
    const int pw = 12, pr = 4;

    auto t_start2 = chrono::high_resolution_clock::now();

    parallel(pr, pw);

    auto t_end2 = chrono::high_resolution_clock::now();
    double elapsed_time_ms2 = chrono::duration<double, milli>(t_end2 - t_start2).count();
    printf(">>Measured time parallel = %f\n", elapsed_time_ms2);

    return 0;
}
