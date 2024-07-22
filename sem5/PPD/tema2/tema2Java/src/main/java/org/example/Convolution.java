package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Convolution {
    public static void main(String[] args) {

        int p = 16;
        //citire din fisier(pentru toate datele)
        String filename = "matrice.txt"; // Numele fișierului text
        int N = -1; // Valoare implicită pentru N
        int M = -1; // Valoare implicită pentru M
        int[][] matrix = null;
        int[][] conv = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                N = Integer.parseInt(line);
                M = Integer.parseInt(br.readLine());
                matrix = new int[N][M];

                for (int i = 0; i < N; i++) {
                    String[] numbers = br.readLine().split(" ");
                    for (int j = 0; j < M; j++) {
                        matrix[i][j] = Integer.parseInt(numbers[j]);
                    }
                }

                conv = new int[3][3];

                for (int i = 0; i < 3; i++) {
                    String[] numbers = br.readLine().split(" ");
                    for (int j = 0; j < 3; j++) {
                        conv[i][j] = Integer.parseInt(numbers[j]);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //aplicarea convolutiei secvential
        long start_t = System.currentTimeMillis();
        convSecventila(N,M,matrix, conv);
        System.out.println("Secvential time: " + (System.currentTimeMillis() - start_t));

        scrieMatrice(matrix, "output1.txt");

        // reinitializare matrice
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // sarim peste linile care contin dimensiunea matricei
            br.readLine(); //prima linie
            br.readLine(); //a doua linie

            for (int i = 0; i < N; i++) {
                String[] numbers = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        CyclicBarrier barrier = new CyclicBarrier(p);
        Thread[] threads = new Thread[p];
        int cat = N/p;
        int R = N%p;
        int start = 0;

        start_t = System.currentTimeMillis();
        for(int i = 0; i<p; i++){
            int end = start+cat;
            if(R>0){
                end++;
                R--;
            }
            threads[i] = new ThreadPtLinii(i, start, end, N, M, matrix, conv, barrier);

            threads[i].start();
            start = end;
        }
        for(int i=0; i<p; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Parallel time for lines: " + (System.currentTimeMillis() - start_t));
        scrieMatrice(matrix, "output2.txt");
//
//        if(verifCorectitudinii("output1.txt", "output2.txt")){
//            System.out.println("Matricea generata paralel este egala cu matricea generata secvential");
//        }
//        else
//            System.out.println("Matricile difera");
    }


    public static int calculate(int startL, int startC, int N, int M, int[][] F, int[][] C){
        int a = 0, b, s = 0, el;
        // avem nevoie de mijlocul kernelului, deci de 3/2 care este 1 (stim ca kernelul are dimensiunea 3 pe 3)
        for (int i = startL - 1; i <= startL + 1; i++) {
            b = 0;
            for (int j = startC - 1; j <= startC + 1; j++) {
                int row = min(max(i, 0), N - 1);
                int col = min(max(j, 0), M - 1);
                el = F[row][col];
                s += el * C[a][b];
                b++;
            }
            a++;
        }
        return s;
    }
    public static void convSecventila(int N, int M, int[][] exp, int[][] conv){
        int[] aux1 = new int[M];
        int[] aux2 = new int[M];
        for(int j =0; j<M; j++){
            aux1[j] = calculate(0,j,N,M,exp,conv);
        }
        for(int i=1; i<N; i++){
            for(int j=0; j<M; j++) {
                aux2[j] = calculate(i, j, N, M, exp, conv);
            }
            System.arraycopy(aux1, 0, exp[i - 1], 0, M);
            System.arraycopy(aux2, 0, aux1, 0, aux1.length);

        }
        System.arraycopy(aux2, 0, exp[N-1], 0, M);
    }

    public static class ThreadPtLinii extends Thread{
        int id, start, end, N, M;
        int[][] exp, conv, buffer;
        CyclicBarrier barrier;
        private static final Object printLock = new Object();

        public ThreadPtLinii(int id, int start, int end, int N, int M, int[][] a, int[][] b, CyclicBarrier barrier) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.N = N;
            this.M = M;
            this.exp = a;
            this.conv = b;
            this.barrier = barrier;
            this.buffer = new int[end-start+2][M];
        }

        public void run(){
            //compunem un buffer
            int k = 0;
            if(start == 0)
                System.arraycopy(exp[start], 0, buffer[k], 0, M);
            else
                System.arraycopy(exp[start-1], 0, buffer[k], 0, M);
            //System.out.println(Arrays.toString(buffer[k]));
            k++;
            for(int i=start; i<end; i++) {
                System.arraycopy(exp[i], 0, buffer[k], 0, M);
                //System.out.println(Arrays.toString(buffer[k]));
                k++;
            }
            if (end == N)
                System.arraycopy(exp[end-1], 0, buffer[k], 0, M);
            else
                System.arraycopy(exp[end], 0, buffer[k], 0, M);

             //Sincronizăm afișarea conținutului buffer-ului -pt debug
//            synchronized (printLock) {
//                System.out.println("Thread " + id + " - Conținutul buffer-ului:");
//                for (int i = 0; i < buffer.length; i++) {
//                    for (int j = 0; j < M; j++) {
//                        System.out.print(buffer[i][j] + " ");
//                    }
//                    System.out.println();
//                }
//            }

            try {
                //System.out.println("Thread " + id + " a terminat de compus buffer ul.");
                barrier.await(); // Așteaptă la barieră
                //System.out.println("Thread " + id + " a trecut de barieră.");

                k=1;
                // Aici calculam convolutia
                for (int i = start; i < end; i++) {
                    for (int j = 0; j < M; j++) {
                        exp[i][j] = calculate(k, j, N, M, buffer, conv);
                    }
                    k++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean verifCorectitudinii(String filename1, String filename2){
        try (FileInputStream fis1 = new FileInputStream(filename1);
             FileInputStream fis2 = new FileInputStream(filename2)) {

            int byte1, byte2;
            while ((byte1 = fis1.read()) != -1) {
                byte2 = fis2.read();
                if (byte1 != byte2) {
                    return false;
                }
            }

            // Verificăm dacă ambele fișiere s-au terminat în același timp
            return fis2.read() == -1;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void scrieMatrice(int[][] matrix, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    writer.write(anInt + " "); // Scrieți fiecare element al matricei, separat de spațiu
                }
                writer.newLine(); // Treci la următorul rând în fișier
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
