// lab2.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <thread>
#include <chrono>

using namespace std;

const int noOfThreads = 4;
const int n = 10;
int a[n], b[n], c[n];
int* A = new int[n];
int* B = new int[n];
int* C = new int[n];

void addArrays(int i) {
    cout << "Sunt un thread-ul " << i << '\n';
}

void suma(int start, int end) {
    for (int i = start; i < end; i++) {
        c[i] = a[i] + b[i];
    }
}

void suma_ciclica(int id, int p) {
    for (int i = id; i < noOfThreads; i=i+p) {
        c[i] = a[i] + b[i];
    }
}

void suma_din(int start, int end) {
    for (int i = start; i < end; i++) {
        C[i] = A[i] + B[i];
    }
}

void suma_ciclica_din(int id, int p) {
    for (int i = id; i < noOfThreads; i = i + p) {
        C[i] = A[i] + B[i];
    }
}

int main()
{
    thread th[noOfThreads];
    /*for (int i = 0; i < noOfThreads; i++) {
        th[i] = thread(addArrays, i);
    }
    for (int i = 0; i < noOfThreads; i++) {
        th[i].join();
    }*/

    for (int i = 0; i < n; i++) {
        a[i] = i;
        b[i] = i;
    }

    int p = n / noOfThreads;
    int r = n % noOfThreads;

    auto t_start = chrono::high_resolution_clock::now();
    int start = 0;
    for (int i = 0; i < noOfThreads; i++) {
        int end = start + p;
        if (r > 0) {
            end++;
            r--;
        }
        th[i] = thread(suma, start, end);
        
        start = end;
    }
    for (int i = 0; i < noOfThreads; i++) {
        th[i].join();
    }
    auto t_end = chrono::high_resolution_clock::now();
    double elapse_time_ms = chrono::duration<double, milli>(t_end - t_start).count();
    cout << "Liniar" << '\n';
    cout << "elapse_time_ms=" << elapse_time_ms << '\n';

    cout << "C= ";
    for (int i = 0; i < n; i++) {
        cout<< c[i] << " ";
    }
    cout << '\n';
    t_start = chrono::high_resolution_clock::now();
    for (int i = 0; i < noOfThreads; i++) {
        th[i] = thread(suma_ciclica, i, p);
    }
    for (int i = 0; i < noOfThreads; i++) {
        th[i].join();
    }
    t_end = chrono::high_resolution_clock::now();
    elapse_time_ms = chrono::duration<double, milli>(t_end - t_start).count();
    cout << "Ciclic" << '\n';
    cout << "elapse_time_ms=" << elapse_time_ms << '\n';
    cout << "C= ";
    for (int i = 0; i < n; i++) {
        cout << c[i] << " ";
    }
    cout << '\n';

    for (int i = 0; i < n; i++) {
        A[i] = i;
        B[i] = i;
    }

    r = n % noOfThreads;
    t_start = chrono::high_resolution_clock::now();
    start = 0;
    for (int i = 0; i < noOfThreads; i++) {
        int end = start + p;
        if (r > 0) {
            end++;
            r--;
        }
        th[i] = thread(suma_din, start, end);

        start = end;
    }
    for (int i = 0; i < noOfThreads; i++) {
        th[i].join();
    }
    t_end = chrono::high_resolution_clock::now();
    elapse_time_ms = chrono::duration<double, milli>(t_end - t_start).count();
    cout << "Liniar dinamic" << '\n';
    cout << "elapse_time_ms=" << elapse_time_ms << '\n';

    cout << "C= ";
    for (int i = 0; i < n; i++) {
        cout << C[i] << " ";
    }
    cout << '\n';
    t_start = chrono::high_resolution_clock::now();
    for (int i = 0; i < noOfThreads; i++) {
        th[i] = thread(suma_ciclica_din, i, p);
    }
    for (int i = 0; i < noOfThreads; i++) {
        th[i].join();
    }
    t_end = chrono::high_resolution_clock::now();
    elapse_time_ms = chrono::duration<double, milli>(t_end - t_start).count();
    cout << "Ciclic" << '\n';
    cout << "elapse_time_ms=" << elapse_time_ms << '\n';
    cout << "C= ";
    for (int i = 0; i < n; i++) {
        cout << C[i] << " ";
    }
    cout << '\n';

    delete[] A;
    delete[] B;
    delete[] C;

    return 0;
}
