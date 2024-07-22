#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <chrono>
#include <cstring>
#include <functional>
#include <cmath>

using namespace std;

constexpr int DEFAULT_TAG = 0;

//functie care trimite un vector de numere intregi(buffer) la o destinatie data(dest), stiind numarul de intregi din vector(count) 
void send_ints(int dest, const int* buffer, int count) {
    MPI_Send(buffer, count, MPI_INT, dest, DEFAULT_TAG, MPI_COMM_WORLD);
}

//functie care primeste un vector de numere intregi(buffer) de la o sursa(from), stiind numarul de intregi din vector(count)
void recv_ints(int from, int* buffer, int count) {
    MPI_Recv(buffer, count, MPI_INT, from, DEFAULT_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
}

//functie care face operatia de broadcast; root-procesul care trimite datele, buffer-vectorul de valori trimis, count- numarul de elemente trimise
void broadcast(int root, int* buffer, int count) {
    MPI_Bcast(buffer, count, MPI_INT, root, MPI_COMM_WORLD);
}

void scatter_ints(int root, int* whole, int* part, int count) {
    MPI_Scatter(whole, count, MPI_INT, part, count, MPI_INT, root, MPI_COMM_WORLD);
}

void gather_ints(int root, int* whole, int* part, int count) {
    MPI_Gather(part, count, MPI_INT, whole, count, MPI_INT, root, MPI_COMM_WORLD);
}

int conv(int* K, int* prvL, int* crtL, int* nxtL, int j, int M) {
    return K[0] * prvL[max(j - 1, 0)] + K[1] * prvL[j] + K[2] * prvL[min(j + 1, M - 1)] +
        K[3] * crtL[max(j - 1, 0)] + K[4] * crtL[j] + K[5] * crtL[min(j + 1, M - 1)] +
        K[6] * nxtL[max(j - 1, 0)] + K[7] * nxtL[j] + K[8] * nxtL[min(j + 1, M - 1)];
}


int main(int argc, char** argv) {
    int N = 1000, M = 1000;
    int num_procs, myid;

    MPI_Init(NULL, NULL);
    MPI_Comm_rank(MPI_COMM_WORLD, &myid);
    MPI_Comm_size(MPI_COMM_WORLD, &num_procs);

    //Varianta 1
 
    int chunk_lines_count = N / (num_procs - 1);
    if (myid == 0) {
        //suntem in parinte

        int K[9];
        int* A = new int[N * M];
        auto t_start = chrono::high_resolution_clock::now();
        string filename = "input.txt";
        ifstream file(filename);
        if (!file.is_open()) {
            cerr << "Could not open file." << endl;
            return 1;
        }

        for (int i = 0; i < 9; i++) {
            file >> K[i];
        }

        broadcast(0, K, 9);
        
        cout<<"Reading "<< chunk_lines_count << " lines at a time"<<'\n';
        
        for (int p = 1; p < num_procs; p++) {
            for (int i = 0; i < chunk_lines_count*M; i++) {
                    file >> A[i];
            }           
            send_ints(p, A, chunk_lines_count * M);
        }
        // Închiderea fișierului
        file.close();
        
        ofstream g("output.txt");
        for (int p = 1; p < num_procs; p++) {
            recv_ints(p, A, chunk_lines_count * M);
        
            for (int i = 0; i < chunk_lines_count; i++) {
                for (int j = 0; j < M; j++)
                    g << A[i * M + j] << " ";
                g << "\n";
            }
        }
        g.close();
        auto t_end = chrono::high_resolution_clock::now();
        double elapsed_time_ms = chrono::duration<double, milli>(t_end - t_start).count();
        cout << ">>Measured time(var1) = " << elapsed_time_ms << " ms\n";

        delete[] A;

    }
    else {

        int K[9];
        broadcast(0, K, 9);

        int n = chunk_lines_count;

        int* prevLine = new int[M];
        int* mat = new int[n * M];
        int* lastLine = new int[M];

        recv_ints(0, mat, n * M);

        //procesul 1 nu are o linie pentru bordura superioara, deci o setam ca fiind prima linie din matricea primita de la procesul 0
        if (myid == 1) {
            memcpy(prevLine, mat, M * sizeof(int));
            send_ints(myid + 1, &mat[(n - 1) * M], M); // trimite procesului cu rank mai mare cu 1, linia pentru prevLine
            recv_ints(myid + 1, lastLine, M); // primeste de la procesul cu rank mai mare cu 1, linia pentru lastLine
        }
        //ultimul proces nu are o bordura inferioara, deci o setam ca fiind ultima linie din matricea primita de la procesul 0
        else if (myid == num_procs - 1) {
            memcpy(lastLine, &mat[(n - 1) * M], M * sizeof(int));
            recv_ints(myid - 1, prevLine, M); //primeste prevLine de la procesul cu rank mai mic cu 1
            send_ints(myid - 1, mat, M); // trimite lastLine la procesul cu rank mai mic cu 1
        }
        else {
            recv_ints(myid - 1, prevLine, M);
            send_ints(myid - 1, mat, M); 

            send_ints(myid + 1, &mat[(n - 1) * M], M);
            recv_ints(myid + 1, lastLine, M);
        }

        int* crtLine = new int[M];
        // prelucrarea datelor
        for (int i = 0; i < n; i++) {
            memcpy(crtLine, &mat[i * M], M * sizeof(int));
            int* nextLine = i < n - 1 ? &mat[(i + 1) * M] : lastLine;
            for (int j = 0; j < M; j++)
                mat[i * M + j] = conv(K, prevLine, crtLine, nextLine, j, M);
            memcpy(prevLine, crtLine, M * sizeof(int));
        }
        // trimite datele la procesul 0
        send_ints(0, mat, n * M);

        delete[] prevLine;
        delete[] mat;
        delete[] lastLine;
        delete[] crtLine;
    }

    //Varianta 2

    //int K[9];
    //int* A = new int[N * M];
    //auto t1_start = chrono::high_resolution_clock::now();
    //string filename = "input.txt";

    //if(myid == 0) {
    //    ifstream file(filename);
    //    if (!file.is_open()) {
    //        cerr << "Could not open file." << endl;
    //        return 1;
    //    }

    //    for (int i = 0; i < 9; i++) {
    //        file >> K[i];
    //    }

    //    for (int i = 0; i < N * M; i++) {
    //        file >> A[i];
    //    }

    //    file.close();
    //}

    //broadcast(0, K, 9);

    //int p_lines_count = N / num_procs;

    //int* mat = new int[p_lines_count * M];

    //auto t2_start = chrono::high_resolution_clock::now();

    //scatter_ints(0, A, mat, p_lines_count* M);

    //int* prevLine = new int[M];
    //int* crtLine = new int[M];
    //int* lastLine = new int[M];

    //if (myid == 0) {
    //    int s = 0, e = min(p_lines_count, M - 1);
    //    memcpy(prevLine, &A[s * M], M * sizeof(int));
    //    memcpy(lastLine, &A[e * M], M * sizeof(int));

    //    for (int p = 1; p < num_procs; p++) {
    //        s = p * p_lines_count - 1;
    //        e = min((p + 1) * p_lines_count, N - 1);

    //        send_ints(p, &A[s * M], M);
    //        send_ints(p, &A[e * M], M);
    //    }
    //}
    //else {
    //    recv_ints(0, prevLine, M);
    //    recv_ints(0, lastLine, M);
    //}

    //for (int i = 0; i < p_lines_count; i++) {
    //    memcpy(crtLine, &mat[i * M], M * sizeof(int));
    //    int* nextLine = i < p_lines_count - 1 ? &mat[(i + 1) * M] : lastLine;
    //    for (int j = 0; j < M; j++)
    //        mat[i * M + j] = conv(K, prevLine, crtLine, nextLine, j, M);
    //    memcpy(prevLine, crtLine, M * sizeof(int));
    //}

    //gather_ints(0, A, mat, p_lines_count * M);

    //if (myid == 0) {
    //    auto t2_end = chrono::high_resolution_clock::now();
    //    double elapsed_t2_ms = chrono::duration<double, milli>(t2_end - t2_start).count();
    //    printf(">>Measured time(var2 interval calcul) = %f\n", elapsed_t2_ms); // t2
    //    }

    //if (myid == 0) {
    //    ofstream g("output.txt");
    //    for (int i = 0; i < N; i++) {
    //        for (int j = 0; j < M; j++)
    //            g << A[i * M + j] << " ";
    //        g << "\n";
    //    }
    //    g.close();

    //    auto t1_end = chrono::high_resolution_clock::now();
    //    double elapsed_t1_ms = chrono::duration<double, milli>(t1_end - t1_start).count();
    //    printf(">>Measured time(var 2 finalizare scriere) = %f\n", elapsed_t1_ms); // t1
    //    }

    //delete[] A;
    //delete[] mat;
    //delete[] prevLine;
    //delete[] crtLine;
    //delete[] lastLine;

    MPI_Finalize();

    //validare date
    /*bool identical = true;
    std::ifstream file1("output1.txt");
    std::ifstream file2("output2.txt");
    int byte1, byte2;
    while ((byte1 = file1.get()) != -1) {
        byte2 = file2.get();
        if (byte1 != byte2) {
            identical = false;
            break;
        }
    }

    if (identical) {
        std::cout << "Matricile sunt identice." << std::endl;
    }
    else {
        std::cout << "Matricile difera." << std::endl;
    }*/
    return 0;
}