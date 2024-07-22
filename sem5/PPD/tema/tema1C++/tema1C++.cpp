// LabCppStatic.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <string>
#include <fstream>
#include <chrono>
#include <ctime>
#include <vector>
#include <thread>

using namespace std;

int N, M, K1, K2, P;
int F[10000][10000], C[100][100], R1[10000][10000], R2[10000][10000], R3[10000][10000];

int calculate(int l, int c) {
    int a = 0, b, s = 0, el;
    int n = N;
    int m = M;
    int halfK1 = K1 / 2;
    int halfK2 = K2 / 2;

    for (int i = l - halfK1; i <= l + halfK1; i++) {
        b = 0;
        for (int j = c - halfK2; j <= c + halfK2; j++) {
            int row = min(max(i, 0), n - 1);
            int col = min(max(j, 0), m - 1);
            el = F[row][col];
            s += el * C[a][b];
            b++;
        }
        a++;
    }
    return s;
}

void getResult() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int cc = calculate(i, j);
            R1[i][j] = cc;
        }
    }
}

void getResultLines(int id, int startLine, int endLine) {
    printf("I'm thread #%d from %d to %d\n", id, startLine, endLine - 1);
    for (int i = startLine; i < endLine; i++) {
        for (int j = 0; j < M; j++) {
            int cc = calculate(i, j);
            R2[i][j] = cc;
        }
    }
}

void getResultColumns(int id, int startColumn, int endColumn) {
    printf("I'm thread #%d from %d to %d\n", id, startColumn, endColumn - 1);
    for (int i = 0; i < N; i++) {
        for (int j = startColumn; j < endColumn; j++) {
            int cc = calculate(i, j);
            R3[i][j] = cc;
        }
    }
}

void printArrays(int K1, int K2, int N, int M, int C[][100], int F[][10000]) {
    cout << K1 << " " << K2 << endl;
    for (int i = 0; i < K1; i++) {
        for (int j = 0; j < K2; j++) {
            cout << C[i][j] << " ";
        }
        cout << endl;
    }

    cout << N << " " << M << endl;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cout << F[i][j] << " ";
        }
        cout << endl;
    }
}

void readData(int& N, int& M, int& K1, int& K2, int mat[][10000], int conv[][100], string fileName) {
    try {
        ifstream f(fileName);
        if (!f.is_open()) cout << "Could not open file!";
        f >> N >> M;
        f >> K1 >> K2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                f >> mat[i][j];
            }
        }
        for (int i = 0; i < K1; i++) {
            for (int j = 0; j < K2; j++) {
                f >> conv[i][j];
            }
        }
        f.close();
    }
    catch (exception& ex) {
        cout << ex.what();
    }
}

void writeMatrix(int mat[][10000], int L, int C, string fileName) {
    try {
        ofstream g(fileName);
        if (!g.is_open())
            cout << "failed to open file\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                g << mat[i][j] << " ";
            }
            if (i < N - 1)
                g << '\n';
        }
        g.close();
    }
    catch (exception& ex) {
        cout << ex.what();
    }
}

bool areFilesIdentical(const string& file1, const string& file2) {
    ifstream stream1(file1, ios::binary);
    ifstream stream2(file2, ios::binary);

    if (!stream1.is_open() || !stream2.is_open()) {
        cout << "Could not open file!";
        return false; // Unable to open one of the files
    }

    char ch1, ch2;
    while (stream1.get(ch1) && stream2.get(ch2)) {
        if (ch1 != ch2) {
            return false; // Files have differing content
        }
    }

    if (stream1.eof())
        stream2.get(ch2);

    // If both files reach the end together, they have identical content
    return stream1.eof() && stream2.eof();
}

int main(int argc, char* argv[])
{
    P = 16;

    //citire date fisier
    readData(N, M, K1, K2, F, C, "date4.txt");

    //static simplu
    auto t_start = std::chrono::high_resolution_clock::now();
    getResult();
    auto t_end = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();

    writeMatrix(R1, N, M, "resultSecvential.txt");

    //static linii
    std::vector<std::thread> th1(P);
    int start = 0;
    int end;
    int cat = N / P;
    int rest = N % P;

    auto t_start1 = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < P; i++) {
        end = start + cat;
        if (rest > 0) {
            end++;
            rest--;
        }

        th1[i] = thread(getResultLines, i, start, end);   // initialize AND START threads!!!
        start = end;
    }

    for (int i = 0; i < P; i++) {
        th1[i].join();
    }

    auto t_end1 = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms1 = std::chrono::duration<double, std::milli>(t_end1 - t_start1).count();
    
    writeMatrix(R2, K1, K2, "resultLines.txt");

    // static coloane
    std::vector<std::thread> th2(P);
    start = 0;
    end;
    cat = M / P;
    rest = M % P;

    auto t_start2 = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < P; i++) {
        end = start + cat;
        if (rest > 0) {
            end++;
            rest--;
        }

        th2[i] = thread(getResultColumns, i, start, end);   // initialize AND START threads!!!
        start = end;
    }

    for (int i = 0; i < P; i++) {
        th2[i].join();
    }

    auto t_end2 = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms2 = std::chrono::duration<double, std::milli>(t_end2 - t_start2).count();
    
    writeMatrix(R3, K1, K2, "resultColumns.txt");

    cout << "Secvential Static" << endl;
    cout << elapsed_time_ms << "\n";
    cout << "ParallelLines Static" << endl;
    cout << elapsed_time_ms1 << endl;
    cout << "ParallelColumns Static" << endl;
    cout << elapsed_time_ms2 << endl;


    if (areFilesIdentical("resultSecvential.txt", "resultLines.txt") && areFilesIdentical("resultSecvential.txt", "resultColumns.txt")) {
        cout << "Files identical";
    }
    else {
        cout << "Files not identical";
        throw new exception("Files are not identical!");
    }

    return 0;
}
