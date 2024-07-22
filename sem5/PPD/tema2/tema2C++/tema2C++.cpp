#include <iostream>
#include <fstream>
#include <vector>
#include <thread>
#include <algorithm>
#include <chrono>
#include <cstring>
#include <condition_variable>
#include <barrier>


using namespace std;
// Numărul de thread-uri
const int p = 4;
barrier bar(p);

// Declarații pentru alocarea și dealocarea matricelor
int** allocateMatrix(int rows, int cols) {
    int** matrix = new int* [rows];
    for (int i = 0; i < rows; ++i) {
        matrix[i] = new int[cols];
    }
    return matrix;
}

void deallocateMatrix(int** matrix, int rows) {
    for (int i = 0; i < rows; ++i) {
        delete[] matrix[i];
    }
    delete[] matrix;
}

// Funcție pentru calculul convoluției pentru un element din matrice
int calculate(int startL, int startC, int N, int M, int** F, int** C) {
    int a = 0, b, s = 0, el;
    for (int i = startL - 1; i <= startL + 1; ++i) {
        b = 0;
        for (int j = startC - 1; j <= startC + 1; ++j) {
            int row = std::min(std::max(i, 0), N - 1);
            int col = std::min(std::max(j, 0), M - 1);
            el = F[row][col];
            s += el * C[a][b];
            ++b;
        }
        ++a;
    }
    return s;
}

// Structură pentru a păstra datele necesare unui thread
struct ThreadData {
    int id;
    int start;
    int end;
    int N;
    int M;
    int** exp;
    int** conv;
    int** buffer;
};

// Funcție pentru calculul convoluției secvențiale
void convSequential(int N, int M, int** exp, int** conv) {
    int* aux1 = new int[M];
    int* aux2 = new int[M];

    for (int j = 0; j < M; ++j) {
        aux1[j] = calculate(0, j, N, M, exp, conv);
    }

    for (int i = 1; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            aux2[j] = calculate(i, j, N, M, exp, conv);
        }

        memcpy(exp[i - 1], aux1, M * sizeof(int));
        memcpy(aux1, aux2, M * sizeof(int));
    }

    memcpy(exp[N - 1], aux2, M * sizeof(int));
    delete[] aux1;
    delete[] aux2;
}

// Funcție pentru rularea unui thread care calculează convoluția pe linii
void runThread(ThreadData& data) {
    int k = 0;

    if (data.start == 0) {
        memcpy(data.buffer[k], data.exp[data.start], data.M * sizeof(int));
    }
    else {
        memcpy(data.buffer[k], data.exp[data.start - 1], data.M * sizeof(int));
    }
    ++k;

    for (int i = data.start; i < data.end; ++i) {
        memcpy(data.buffer[k], data.exp[i], data.M * sizeof(int));
        ++k;
    }

    if (data.end == data.N) {
        memcpy(data.buffer[k], data.exp[data.end - 1], data.M * sizeof(int));
    }
    else {
        memcpy(data.buffer[k], data.exp[data.end], data.M * sizeof(int));
    }

    bar.arrive_and_wait();

    int j = 1;
    for (int i = data.start; i < data.end; ++i) {
        for (int col = 0; col < data.M; ++col) {
            data.exp[i][col] = calculate(j, col, data.N, data.M, data.buffer, data.conv);
        }
        ++j;
    }
}

int main() {
    int N = -1; // Valoare implicită pentru N
    int M = -1; // Valoare implicită pentru M
    int** matrix = nullptr;
    int** conv = nullptr;

    // Citire din fișier
    string filename = "date2.txt";
    ifstream file(filename);

    if (!file.is_open()) {
        cerr << "Could not open file." << std::endl;
        return 1;
    }

    file >> N >> M;
    matrix = allocateMatrix(N, M);
    conv = allocateMatrix(3, 3);

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            file >> matrix[i][j];
        }
    }

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            file >> conv[i][j];
        }
    }

    file.close();

    // Aplicarea convoluției secvențiale
    auto startSeq = std::chrono::high_resolution_clock::now();
    convSequential(N, M, matrix, conv);
    auto endSeq = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> elapsedSeq = endSeq - startSeq;
    std::cout << "Sequential time: " << elapsedSeq.count() << " ms" << std::endl;

    // Scriere matrice
    std::ofstream outputFile("output1.txt");
    if (outputFile.is_open()) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                outputFile << matrix[i][j] << " ";
            }
            outputFile << "\n";
        }
        outputFile.close();
    }
    else {
        std::cerr << "Failed to open the output file." << std::endl;
        return 1;
    }

    // Reinitializare matrice
    file.open(filename);

    if (!file.is_open()) {
        cerr << "Could not open file." << std::endl;
        return 1;
    }

    file >> N >> M;

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            file >> matrix[i][j];
        }
    }

    file.close();

    // Aplicarea convoluției paralele pe linii
    vector<ThreadData> threadData(p);
    vector<thread> threads(p);
    int cat = N / p;
    int R = N % p;
    int start = 0;

    auto startParallel = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < p; ++i) {
        int end = start + cat;
        if (R > 0) {
            ++end;
            --R;
        }

        threadData[i] = { i, start, end, N, M, matrix, conv, allocateMatrix(end - start + 2, M) };
        threads[i] = thread(runThread, ref(threadData[i]));

        start = end;
    }

    for (int i = 0; i < p; ++i) {
        threads[i].join();
    }
    auto endParallel = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> elapsedParallel = endParallel - startParallel;
    std::cout << "Parallel time for lines: " << elapsedParallel.count() << " ms" << std::endl;

    // Scriere matrice
    /*outputFile.open("output2.txt");
    if (outputFile.is_open()) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                outputFile << matrix[i][j] << " ";
            }
            outputFile << "\n";
        }
        outputFile.close();
    }
    else {
        std::cerr << "Failed to open the output file." << std::endl;
        return 1;
    }*/

    // Verificarea corectitudinii
    bool identical = true;
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
        std::cout << "Matricea generata paralel este egala cu matricea generata secvential." << std::endl;
    }
    else {
        std::cout << "Matricile difera." << std::endl;
    }

    return 0;
}
