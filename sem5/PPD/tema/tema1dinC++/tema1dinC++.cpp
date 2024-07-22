#include <iostream>
#include <thread>
#include <chrono>
#include <ctime>
#include <fstream>
#include <string>
#include <thread>
#include <vector>

using namespace std;

int K1, K2, N, M;
int P = 4;

int** allocate(int N, int M) {
    int** a = new int* [N];
    for (int i = 0; i < N; i++)
        a[i] = new int[M];
    return a;
}

void deallocate(int** a, int N) {
    for (int i = 0; i < N; i++)
        delete[] a[i];
    delete[] a;
}

void readData(int& N, int& M, int& K1, int& K2, int**& F, int**& C, string fileName) {
    try {
        ifstream f(fileName);
        if (!f.is_open()) cout << "Could not open file!";
        f >> N >> M;
        f >> K1 >> K2;
        F = allocate(N, M);
        C = allocate(K1, K2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                f >> F[i][j];
            }
        }
        for (int i = 0; i < K1; i++) {
            for (int j = 0; j < K2; j++) {
                f >> C[i][j];
            }
        }
        f.close();
    }
    catch (exception& ex) {
        cout << ex.what();
    }
}

int calculate(int l, int c, int N, int M, int K1, int K2, int** F, int** C, int** R) {
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


void getResult(int** F, int** C, int** R) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int cc = calculate(i, j, N, M, K1, K2, F, C, R);
            R[i][j] = cc;
        }
    }
}

void getResultLines(int id, int startLine, int endLine, int** F, int** C, int** R) {
    printf("I'm thread #%d from %d to %d\n", id, startLine, endLine - 1);
    for (int i = startLine; i < endLine; i++) {
        for (int j = 0; j < M; j++) {
            int cc = calculate(i, j, N, M, K1, K2, F, C, R);
            R[i][j] = cc;
        }
    }
}

void getResultColumns(int id, int startColumn, int endColumn, int** F, int** C, int** R) {
    printf("I'm thread #%d from %d to %d\n", id, startColumn, endColumn - 1);
    for (int i = 0; i < N; i++) {
        for (int j = startColumn; j < endColumn; j++) {
            int cc = calculate(i, j, N, M, K1, K2, F, C, R);
            R[i][j] = cc;
        }
    }
}

void printArrays(int K1, int K2, int N, int M, int** C, int** F) {
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

//void readData(int& N, int& M, int& K1, int& K2, string fileName) {
//    try {
//        ifstream f(fileName);
//        if (!f.is_open()) cout << "Could not open file!";
//        f >> N >> M;
//        f >> K1 >> K2;
//        f.close();
//    }
//    catch (exception& ex) {
//        cout << ex.what();
//    }
//}
//
//void readMatrix(int** mat, int L, int C, string fileName) {
//    try {
//        ifstream f(fileName);
//        if (!f.is_open()) cout << "Could not open file!";
//        for (int i = 0; i < L; i++) {
//            for (int j = 0; j < C; j++) {
//                f >> mat[i][j];
//            }
//        }
//        f.close();
//    }
//    catch (exception& ex) {
//        cout << ex.what();
//    }
//}

void writeMatrix(int** mat, int L, int C, string fileName) {
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

bool areFilesIdentical(const std::string& file1, const std::string& file2) {
    std::ifstream stream1(file1, std::ios::binary);
    std::ifstream stream2(file2, std::ios::binary);

    if (!stream1.is_open() || !stream2.is_open()) {
        cout << "Could not open file!";
        return false; // Unable to open one of the files
    }

    char ch1, ch2;
    while (stream1.get(ch1) && stream2.get(ch2)) {
        //cout << ch1 << "---" << ch2 << endl;
        if (ch1 != ch2) {
            //cout << ch1 << " different from " << ch2;
            return false; // Files have differing content
        }
    }

    if (stream1.eof())
        stream2.get(ch2);

    // If both files reach the end together, they have identical content
    return stream1.eof() && stream2.eof();
}
//
//int** allocate(int N, int M) {
//    int** a = new int* [N];
//    for (int i = 0; i < N; i++)
//        a[i] = new int[M];
//    return a;
//}
//
//void deallocate(int** a, int N, int M) {
//    for (int i = 0; i < N; i++)
//        delete[] a[i];
//    delete[] a;
//}

int main(int argc, char* argv[])
{
    int** F = nullptr, ** C = nullptr, ** R1 = nullptr, ** R2 = nullptr, ** R3 = nullptr;
    int P = 16;

    readData(N, M, K1, K2, F, C, "date4.txt");

    // Secvential
    R1 = allocate(N, M);
    auto t_start = std::chrono::high_resolution_clock::now();
    getResult(F, C, R1);
    auto t_end = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    
    writeMatrix(R1, N, M, "resultSecvential.txt");
    
    deallocate(R1, N);

    //Linii
    //thread th[P];
    std::vector<std::thread> th1(P);
    int start = 0;
    int end;
    int cat = N / P;
    int rest = N % P;

    R2 = allocate(N, M);
    auto t_start1 = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < P; i++) {
        end = start + cat;
        if (rest > 0) {
            end++;
            rest--;
        }

        th1[i] = thread(getResultLines, i, start, end, F, C, R2);   // initialize AND START threads!!!
        start = end;
    }

    for (int i = 0; i < P; i++) {
        th1[i].join();
    }

    auto t_end1 = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms1 = std::chrono::duration<double, std::milli>(t_end1 - t_start1).count();
    
    
    writeMatrix(R2, N, M, "resultLines.txt");
    deallocate(R2, N);

    //Coloane
    //thread th[P];
    std::vector<std::thread> th2(P);
    start = 0;
    end;
    cat = M / P;
    rest = M % P;

    R3 = allocate(N, M);
    auto t_start2 = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < P; i++) {
        end = start + cat;
        if (rest > 0) {
            end++;
            rest--;
        }

        th2[i] = thread(getResultColumns, i, start, end, F, C, R3);   // initialize AND START threads!!!
        start = end;
    }

    for (int i = 0; i < P; i++) {
        th2[i].join();
    }

    auto t_end2 = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms2 = std::chrono::duration<double, std::milli>(t_end2 - t_start2).count();
    
    writeMatrix(R3, K1, K2, "resultColumns.txt");
    deallocate(R3, N);

    deallocate(C, K1);
    deallocate(F, N);

    cout << "Secvential Dinamic" << endl;
    cout << elapsed_time_ms << endl;
    cout << "ParallelLinesDinamic" << endl;
    cout << elapsed_time_ms1 << endl;
    cout << "ParallelColumns Dinamic" << endl;
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
