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

struct Node {
    Participant participant;
    Node* next;
    mutex node_mutex;

    Node(const Participant& p, Node* next = nullptr) : participant(p), next(next), node_mutex() {}
};

class MyCostumList {
private:
 
    Node* lock_node(Node* node) {
        if (node)
            node->node_mutex.lock();
        return node;
    }

    void unlock_node(Node* node) {
        if (node)
            node->node_mutex.unlock();
    }

    Node* start;
    Node* end;

public:
    MyCostumList() {
        end = new Node(Participant());
        start = new Node(Participant(), end);
    }

    bool is_sorted(const Participant& p, const Participant& q) {
        return p.score > q.score || (p.score == q.score && p.id <= q.id);
    }

    bool remove_if_exists(function<bool(const Participant&)> pred, Participant& result)
    {
        Node* node1 = lock_node(start);
        Node* node2 = lock_node(node1->next);

        if (node2 == end) // empty list
        {
            unlock_node(node1);
            unlock_node(node2);
            return false;
        }

        Node* node3 = lock_node(node2->next);

        while (node3 != nullptr)
        {
            if (pred(node2->participant))
            {
                result = node2->participant;
                node1->next = node3;
                unlock_node(node1);
                unlock_node(node2);
                unlock_node(node3);
                delete node2;
                return true;
            }

            unlock_node(node1);
            node1 = node2;
            node2 = node3;
            node3 = lock_node(node3->next);
        }

        unlock_node(node1);
        unlock_node(node2);
        unlock_node(node3);

        return false;
    }

    void insert(const Participant& p) {
        Node* node = new Node(p);

        Node* node1 = lock_node(start);
        Node* node2 = lock_node(node1->next);

        if (node2 == end) {
            node1->next = node;
            node->next = node2;
            unlock_node(node1);
            unlock_node(node2);
            return;
        }

        if (is_sorted(node->participant, node2->participant)) {
            node1->next = node;
            node->next = node2;
            unlock_node(node1);
            unlock_node(node2);
            return;
        }

        unlock_node(node1), node1 = node2, node2 = lock_node(node1->next);

        while (node2 != end) {
            if (is_sorted(node1->participant, node->participant) && is_sorted(node->participant, node2->participant)) {
                node1->next = node;
                node->next = node2;
                unlock_node(node1);
                unlock_node(node2);
                return;
            }
            unlock_node(node1), node1 = node2, node2 = lock_node(node1->next);
        }

        node1->next = node;
        node->next = node2;
        unlock_node(node1);
        unlock_node(node2);
    }

    ~MyCostumList() {
        for (Node* n = start, *tmp; n != nullptr;) {
            tmp = n;
            n = n->next;
            delete tmp;
        }
    }

    void iterate_sync(function<void(const Participant&)> callback) const {
        for (Node* n = start->next; n != end; n = n->next) {
            callback(n->participant);
        }
    }
};


class ParticipantList : MyCostumList {
private:
    set<int> disqualified;
    map<int, mutex> id_mutex;
    mutex list_mutex;

public:

    void add_score(const Participant& p) {
        list_mutex.lock();
        lock_guard<mutex> lk(id_mutex[p.id]);
        list_mutex.unlock();

        if (disqualified.find(p.id) != disqualified.end())
            return;

        Participant tmp = { p.id, 0, p.country };
        remove_if_exists([&p](const Participant& r) { return r.id == p.id; }, tmp);

        if (p.score < 0) {
            disqualified.insert(p.id);
            return;
        }
        else {
            tmp.score += p.score;
            insert(tmp);
        }
    }

    friend ostream& operator << (ostream& o, const ParticipantList& l)
    {
        l.iterate_sync([&o](const Participant& r) { o << r.id << " " << r.score << " " << r.country << "\n"; });
        o << "Eliminati:\n";
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

void producer(ParticipantList* r_list, Queue* queue, int pr, int pid)
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

void consumer(ParticipantList* r_list, Queue* queue, int pid)
{
    while (true) {
        Participant p;
        if (!queue->dequeue(p))
            break;
        r_list->add_score(p);
    }
}

void parallel(int pr, int pw)
{
    ParticipantList r_list;
    Queue queue;

    thread* consumers = new thread[pw]{};
    for (int p = 0; p < pw; p++) consumers[p] = thread(consumer, &r_list, &queue, p);


    thread* producers = new thread[pr]{};
    for (int p = 0; p < pr; p++) producers[p] = thread(producer, &r_list, &queue, pr, p);

    for (int p = 0; p < pr; p++) producers[p].join();

    delete[] producers;

    queue.shutdown();

    for (int p = 0; p < pw; p++) consumers[p].join();

    ofstream g("result_parallel.txt");
    g << r_list;
    g.close();
}


void validate()
{
    bool identical = true;
    std::ifstream file1("result_secv.txt");
    std::ifstream file2("result_parallel.txt");
    int byte1, byte2;
    while ((byte1 = file1.get()) != -1)
    {
        byte2 = file2.get();
        if (byte1 != byte2)
        {
            identical = false;
            break;
        }
    }

    if (identical)
    {
        std::cout << "Rezultatele sunt identice." << std::endl;
    }
    else
    {
        std::cout << "Rezultatele difera." << std::endl;
    }
}

int main(int argc, char** argv)
{
    auto t_start = std::chrono::high_resolution_clock::now();
    int pw = 12;
    int pr = 4;
    parallel(pr, pw);

    auto t_end = std::chrono::high_resolution_clock::now();
    auto elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    printf(">>Measured time (parallel) = %f\n", elapsed_time_ms);

    validate();

    return 0;
}