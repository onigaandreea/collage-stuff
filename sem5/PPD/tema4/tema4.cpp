#include <iostream>
#include <filesystem>
#include <fstream>
#include <string>
#include <set>
#include <chrono>

#include <windows.h>

using namespace std;

class ParticipantList
{
private:
    struct Node
    {
        int part_id, score = 0;
        Node* next;
    };
    Node* head = nullptr;

    Node* find(int participantID)
    {
        //if found removed from the list
        Node* n = head, * prev = nullptr;
        while (n != nullptr)
        {
            if (n->part_id == participantID)
            {
                if (prev != nullptr)
                    prev->next = n->next;
                else
                    head = n->next;
                n->next = nullptr;
                return n;
            }
            prev = n, n = n->next;
        }

        return new Node{ participantID, 0, nullptr };
    }

    bool order(Node* n1, Node* n2)
    {
        if (n1->score > n2->score || (n1->score == n2->score && n1->part_id <= n2->part_id))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void insert_node(Node* target)
    {
        if (head == nullptr)
        {
            //empty list
            head = target;
            return;
        }
        if (order(target, head))
        {
            //insert at the begining of the list
            target->next = head;
            head = target;
            return;
        }

        Node* n = head;
        while (n->next != nullptr)
        {
            if (order(n, target) && order(target, n->next))
            {
                target->next = n->next;
                n->next = target;
                return;
            }
            n = n->next;
        }

        //insert at the end of the list
        n->next = target;
    }

    set<int> disqualified;
    mutex mutex;
    bool is_parallel;

public:
    ParticipantList(bool is_parallel = false) : is_parallel(is_parallel) { }

    void add_score(int participant, int score)
    {
        if (is_parallel) mutex.lock();
        ([&]()
            {
                if (disqualified.contains(participant)) return;
                Node* n = find(participant);
                if (score < 0)
                {
                    delete n;
                    disqualified.insert(participant);
                    return;
                }
                n->score += score;
                insert_node(n);
            }
        )();
        if (is_parallel) mutex.unlock();
    }

    friend ostream& operator << (ostream& o, const ParticipantList& pl)
    {
        for (Node* n = pl.head; n; n = n->next)
            o << n->part_id << " " << n->score << "\n";
        o << "Eliminati:\n";
        for (auto d : pl.disqualified) o << d << "\n";
        return o;
    }
};


struct Participant
{
    int part_id;
    int score;
};


class Queue
{
private:
    struct Node
    {
        Participant item;
        Node* next;
    };
    Node* head = nullptr;
    Node* last = nullptr;

    mutex mutex;
    bool is_parallel;

public:
    Queue(bool is_parallel = false) : is_parallel(is_parallel) { }

    void enqueue(const Participant& item)
    {
        Node* n = new Node{ item, nullptr };
        if (head == nullptr)
        {
            head = last = n;
            return;
        }
        last->next = n;
        last = n;
    }

    Participant dequeue()
    {
        if (head == nullptr)
        {
            throw exception("Nothing to dequeue");
        }
        Participant result = head->item;
        head = head->next;
        if (head == nullptr) last = nullptr;
        return result;
    }

    void try_enqueue(const Participant& item)
    {
        if (is_parallel) mutex.lock();

        Node* n = new Node{ item, nullptr };
        if (head == nullptr)
        {
            head = last = n;
            goto _end;
        }
        last->next = n;
        last = n;

    _end:
        if (is_parallel) mutex.unlock();
    }

    bool try_dequeue(Participant& result)
    {
        mutex.lock();
        if (head == nullptr)
        {
            mutex.unlock();
            return false;
        }

        result = head->item;
        head = head->next;
        if (head == nullptr) last = nullptr;

        mutex.unlock();
        return true;
    }

    bool is_empty()
    {
        if (is_parallel) mutex.lock();
        bool result = head == nullptr;
        if (is_parallel) mutex.unlock();
        return result;
    }
};


void producer(Queue* queue, int pr, int pid)
{
    int q = 50 / pr, r = 50 % pr;
    int start = pid * q + (r <= pid ? r : pid);
    int end = (pid + 1) * q + (r <= (pid + 1) ? r : pid + 1);
    printf("Started producer %i %i\n", start, end);

    //cout << start << " " << end << "\n";
    for (int k = start; k < end; k++)
    {
        int country = k / 10 + 1, part = k % 10 + 1;
        ifstream f("data\\C" + to_string(country) + "_P" + to_string(part) + ".txt");  

        for (int part, score, k = 0; k < 50000 && (f >> part >> score); k++)
        {
            queue->try_enqueue({ part, score });
        }
        f.close();
    }
    printf("Producer %i finished.\n", pid);
}

void consumer(Queue* queue, std::atomic<bool>* work_needed, ParticipantList* parts_list, int pid)
{
    Participant x;
    while (work_needed->load())
    {
        while (!queue->is_empty())
        {
            if (queue->try_dequeue(x))
            {
                parts_list->add_score(x.part_id, x.score);
            }
            else
                Sleep(1);
        }
    }
    printf("Consumer %i finished.\n", pid);
}


void parallel(int pr, int pw)
{
    ParticipantList parts_list(true);
    Queue q(true);

    std::atomic<bool> work_needed(true);

    thread* consumers = new thread[pw]{};
    for (int p = 0; p < pw; p++) consumers[p] = thread(consumer, &q, &work_needed, &parts_list, p);


    thread* producers = new thread[pr]{};
    for (int p = 0; p < pr; p++) producers[p] = thread(producer, &q, pr, p);

    for (int p = 0; p < pr; p++) producers[p].join();

    work_needed.store(false);
    for (int p = 0; p < pw; p++) consumers[p].join();

    delete[] producers;

    ofstream g("result_paralel.txt");
    g << parts_list;
    g.close();
}

void sequencial()
{
    ParticipantList participants;

    for (const auto& entry : filesystem::directory_iterator("data"))
    {
        cout << entry.path() << "\n";
        ifstream f(entry.path());
        for (int id_part, score; f >> id_part >> score; participants.add_score(id_part, score));
        f.close();
    }

    ofstream g("result_secv.txt");
    g << participants;
    g.close();
}

void generateData()
{
    filesystem::create_directory("data");

    for (int country = 1; country <= 5; country++)
    {
        int nr_part = 80 + rand() % 20;
        for (int i = 1; i <= 10; i++)
        {
            ofstream f("data\\C" + to_string(country) + "_P" + to_string(i) + ".txt");

            for (int k = 1; k <= nr_part; k++)
            {
                int score = (unsigned)rand() % 50 == 4 ? -1 : (unsigned)rand() % 11;
                f << 100 * country + k << " " << score << "\n";
            }

            f.close();
        }
    }
}

void validate() {
    bool identical = true;
    std::ifstream file1("result_secv.txt");
    std::ifstream file2("result_paralel.txt");
    int byte1, byte2;
    while ((byte1 = file1.get()) != -1) {
        byte2 = file2.get();
        if (byte1 != byte2) {
            identical = false;
            break;
        }
    }

    if (identical) {
        std::cout << "Rezultatele sunt identice." << std::endl;
    }
    else {
        std::cout << "Rezultatele difera." << std::endl;
    }
}

int main(int argc, char** argv)
{
    auto t_start = std::chrono::high_resolution_clock::now();
    //generateData();
    sequencial();
    auto t_end = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    printf(">>Measured time (secvential)= %f\n", elapsed_time_ms);

    t_start = std::chrono::high_resolution_clock::now();
    int pw = 16;
    int pr = 2;
    parallel(pr, pw);
    
    t_end = std::chrono::high_resolution_clock::now();
    elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    printf(">>Measured time (paralel)= %f\n", elapsed_time_ms);

    validate();

    return 0;
}