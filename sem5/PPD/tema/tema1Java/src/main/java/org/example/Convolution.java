package org.example;

import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Convolution {
    public static void main(String[] args) {

        int p = 16;
        //citire din fisier(pentru toate datele)
        String filename = "date4.txt"; // Numele fișierului text
        int N = -1; // Valoare implicită pentru N
        int M = -1; // Valoare implicită pentru M
        int[][] matrix = null;
        int n = -1;
        int m = -1;
        int[][] conv = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                N = Integer.parseInt(line);
                M = Integer.parseInt(br.readLine());
                n = Integer.parseInt(br.readLine());
                m = Integer.parseInt(br.readLine());
                matrix = new int[N][M];

                for (int i = 0; i < N; i++) {
                    String[] numbers = br.readLine().split(" ");
                    for (int j = 0; j < M; j++) {
                        matrix[i][j] = Integer.parseInt(numbers[j]);
                    }
                }


                conv = new int[n][m];

                for (int i = 0; i < n; i++) {
                    String[] numbers = br.readLine().split(" ");
                    for (int j = 0; j < m; j++) {
                        conv[i][j] = Integer.parseInt(numbers[j]);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //aplicarea convolutiei secvential
        long start_t = System.currentTimeMillis();
        int[][] result1 = convSecventila(N,M,matrix, n, conv);
        System.out.println("Secvential time: " + (System.currentTimeMillis() - start_t));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int i = 0; i < result1.length; i++) {
                for (int j = 0; j < result1[i].length; j++) {
                    writer.write(result1[i][j] + " "); // Scrieți fiecare element al matricei, separat de spațiu
                }
                writer.newLine(); // Treci la următorul rând în fișier
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] result2 = new int[N][M];
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
            threads[i] = new ThreadPtLinii(i, start, end, N, M, n, matrix, conv, result2);
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


        int[][] result3 = new int[N][M];
        threads = new Thread[p];
        cat = M/p;
        R = M%p;
        start = 0;

        start_t = System.currentTimeMillis();
        for(int i = 0; i<p; i++){
            int end = start+cat;
            if(R>0){
                end++;
                R--;
            }
            threads[i] = new ThreadPtColoane(i, start, end, N, M, n, matrix, conv, result3);
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

        System.out.println("Parallel time for columns: " + (System.currentTimeMillis() - start_t));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output2.txt"))) {
            for (int i = 0; i < result3.length; i++) {
                for (int j = 0; j < result3[i].length; j++) {
                    writer.write(result3[i][j] + " "); // Scrieți fiecare element al matricei, separat de spațiu
                }
                writer.newLine(); // Treci la următorul rând în fișier
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Afișează rezultatul convoluției
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(result[i][j] + " ");
//            }
//            System.out.println();
//        }


        if(verifCorectitudinii(result2, result3) == 0){
            System.out.println("Matricea generata paralel este egala cu matricea generata secvential");
        }
        else
            System.out.println("Matricile difera");
    }


    public static int calculate(int startL, int startC, int N, int M, int K1, int K2, int[][] F, int[][] C){
        int a = 0, b, s = 0, el;
        int n = N;
        int m = M;
        int halfK1 = K1 / 2;
        int halfK2 = K2 / 2;

        for (int i = startL - halfK1; i <= startL + halfK1; i++) {
            b = 0;
            for (int j = startC - halfK2; j <= startC + halfK2; j++) {
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
    public static int[][] convSecventila(int N, int M, int[][] exp, int n, int[][] conv){
        int[][] result = new int[N][M];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                result[i][j] = calculate(i, j, N, M, n, n, exp, conv);

        return result;
    }

    public static class ThreadPtLinii extends Thread{
        int id, start, end, N, M, n;
        int[][] exp,conv, res;

        public ThreadPtLinii(int id, int start, int end, int N, int M, int n, int[][] a, int[][] b, int[][] c) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.N=N;
            this.M = M;
            this.n = n;
            this.exp = a;
            this.conv = b;
            this.res = c;
        }
        public void run(){
            //System.out.println("Hey eu sunt thredul cu id " + id);
            for(int i=start; i<end; i++)
                for(int j=0; j<M; j++)
                    res[i][j] = calculate(i, j, N, M, n, n, exp, conv);
        }
    }

    public static class ThreadPtColoane extends Thread{
        int id, start, end, N, M, n;
        int[][] exp,conv, res;

        public ThreadPtColoane(int id, int start, int end, int N, int M, int n, int[][] a, int[][] b, int[][] c) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.N = N;
            this.M = M;
            this.n = n;
            this.exp = a;
            this.conv = b;
            this.res = c;
        }

        public void run(){
            //System.out.println("Hey eu sunt thredul cu id " + id);
            for(int i=0; i< N; i++)
                for(int j=start; j<end; j++)
                    res[i][j] = calculate(i, j, N , M, n, n, exp, conv);
        }
    }

    public static int verifCorectitudinii(int[][] matrix, int[][] matrix1){
        String filename = "output.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            for (int i = 0; i < matrix.length; i++) {
                String[] numbers = br.readLine().split(" ");
                for (int j = 0; j < matrix[0].length; j++)
                    if(matrix[i][j] != Integer.parseInt(numbers[j]) || matrix1[i][j] != Integer.parseInt(numbers[j])){
                        return 1;
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
