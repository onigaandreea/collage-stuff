#include <iostream>
#include <mpi.h>
#include <stdlib.h>

using namespace std;

void printVector(int v[], int n) {
    for (int i = 0; i < n; i++) {
        cout << v[i] << " ";
    }
    cout << endl;
}

int main(int argc, char* argv[]) {
    int myid, numprocs, namelen;
    char processor_name[MPI_MAX_PROCESSOR_NAME];

    const int n = 10;
    int a[n], b[n], c[n];
    int start=0;
    int end=0;
    MPI_Status status;

    MPI_Init(NULL, NULL);
    MPI_Comm_rank(MPI_COMM_WORLD, &myid);
    MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
    MPI_Get_processor_name(processor_name, &namelen);

    /*int auxLen = n / numprocs;
    int* auxA = new int[auxLen];
    int* auxB = new int[auxLen];
    int* auxC = new int[auxLen];*/

    int auxLen = n / numprocs;
    int maxChunckSize = auxLen + 1;
    int* auxA = new int[maxChunckSize];
    int* auxB = new int[maxChunckSize];
    int* auxC = new int[maxChunckSize];

    if (myid == 0) {
        for (int i = 0; i < n; i++) {
            a[i] = rand() % 10;
            b[i] = rand() % 10;
        }
    }

    int* pstart = new int[numprocs];
    int* pcnt = new int[numprocs];
    int q = n / numprocs, r = n % numprocs;
    
    for (int p = 0; p < numprocs; p++) {
        end = start + q + (r > 0), r--;
        pstart[p] = start;
        pcnt[p] = end - start;
        start = end;
    }

    MPI_Scatterv(a, pcnt, pstart, MPI_INT, auxA, maxChunckSize, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Scatterv(b, pcnt, pstart, MPI_INT, auxB, maxChunckSize, MPI_INT, 0, MPI_COMM_WORLD);

    /*MPI_Scatter(a, auxLen, MPI_INT, auxA, auxLen, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Scatter(b, auxLen, MPI_INT, auxB, auxLen, MPI_INT, 0, MPI_COMM_WORLD);*/

    for (int i = 0; i < auxLen; i++)
        auxC[i] = auxA[i] + auxB[i];

    //MPI_Gather(auxC, auxLen, MPI_INT, c, auxLen, MPI_INT, 0, MPI_COMM_WORLD);

    MPI_Gatherv(auxC, pcnt[myid], MPI_INT, c, pcnt, pstart, MPI_INT, 0, MPI_COMM_WORLD);

    if (myid == 0) {
        printVector(a, n);
        printVector(b, n);
        printVector(c, n);
    }

    MPI_Finalize();
    return 0;
}


//int main2(int argc, char* argv[])
//{
//
//    int myid, numprocs, namelen;
//    char processor_name[MPI_MAX_PROCESSOR_NAME];
//
//    const int n = 10;
//    int a[n], b[n], c[n];
//    int start;
//    int end;
//    MPI_Status status;
//
//    MPI_Init(NULL, NULL);
//    MPI_Comm_rank(MPI_COMM_WORLD, &myid);
//    MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
//    MPI_Get_processor_name(processor_name, &namelen);
//
//    if (myid == 0) {
//        for (int i = 0; i < n; i++) {
//            a[i] = rand() % 10;
//            b[i] = rand() % 10;
//        }
//        start = 0;
//        end = 0;
//        int q = n / (numprocs - 1);
//        int r = n % (numprocs - 1);
//
//        for (int p = 1; p < numprocs; p++) {
//            end = start + q + (r > 0);
//            r--;
//
//            //cout << "Pentru p=" << p << " s,e = " << start << "," << end << " \n";
//
//            MPI_Send(&start, 1, MPI_INT, p, 0, MPI_COMM_WORLD);
//            MPI_Send(&end, 1, MPI_INT, p, 0, MPI_COMM_WORLD);
//
//            MPI_Send(a + start, (end - start), MPI_INT, p, 0, MPI_COMM_WORLD);
//            MPI_Send(b + start, (end - start), MPI_INT, p, 0, MPI_COMM_WORLD);
//
//            start = end;
//        }
//        for (int p = 1; p < numprocs; p++) {
//
//            MPI_Recv(&start, 1, MPI_INT, p, 0, MPI_COMM_WORLD, &status);
//            MPI_Recv(&end, 1, MPI_INT, p, 0, MPI_COMM_WORLD, &status);
//            MPI_Recv(c + start, (end - start), MPI_INT, p, 0, MPI_COMM_WORLD, &status);
//        }
//        printVector(a, n);
//        printVector(b, n);
//        printVector(c, n);
//
//    }
//    else //copil
//    {
//        MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//        MPI_Recv(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//
//        cout << "Pentru p=" << myid << " s,e = " << start << "," << end << " \n";
//
//        MPI_Recv(a + start, (end - start), MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//        MPI_Recv(b + start, (end - start), MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//
//        for (int i = start; i < end; i++) {
//            c[i] = a[i] + b[i];
//        }
//        MPI_Send(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
//        MPI_Send(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
//        MPI_Send(c + start, (end - start), MPI_INT, 0, 0, MPI_COMM_WORLD);
//    }
//
//
//    MPI_Finalize();
//    return 0;
//
//}