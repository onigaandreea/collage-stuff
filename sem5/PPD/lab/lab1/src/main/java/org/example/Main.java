package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //exemplu de utilizare thread-uri
        int p=4;
        Thread[] thread = new Thread[p];
        for(int i=0; i<p; i++)
        {
            thread[i] = new MyThread(i);
            thread[i].start();
        }
        for(int i=0; i<p; i++){
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //initializare problema
        Random rand = new Random();
        int N=10;
        int P=4;
        int L=5;
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        for(int i=0; i<A.length; i++){
            A[i] = rand.nextInt(L)+1;
            B[i] = rand.nextInt(L)+1;
            C[i] = 0;
        }

        Thread[] threads = new Thread[P];

        //Varianta 1 de rezolvare cu start si end, secvential
        int cat = N/P;
        int R = N%P;
        int start = 0;

        for(int i = 0; i<P; i++){
            int end = start+cat;
            if(R>0){
                end++;
                R--;
            }
            threads[i] = new CustomThread(i, start, end, A, B, C);
            threads[i].start();
            start = end;
        }
        for(int i=0; i<P; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        System.out.println(Arrays.toString(C));


        //Varianta 2 de rezolvare ciclica
        for(int i = 0; i<P; i++){
            threads[i] = new CustomThread1(i, N, P, A, B, C);
            threads[i].start();
        }

        for(int i=0; i<P; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        System.out.println(Arrays.toString(C));

    }

    public static class MyThread extends Thread{
        int id;

        public MyThread(int id) {
            this.id = id;
        }

        public void run(){
            System.out.println("Hey eu sunt thredul cu id " + id);
        }
    }

    public static class CustomThread extends Thread{
        int id, start, end;
        int[] A,B,C;

        public CustomThread(int id, int start, int end, int[] a, int[] b, int[] c) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.A = a;
            this.B = b;
            this.C = c;
        }

        public void run(){
            System.out.println("Hey eu sunt thredul cu id " + id);
            for(int i =start; i<end; i++){
                C[i] = A[i] +B[i];
            }
        }
    }

    public static class CustomThread1 extends Thread{
        int id, n, p;
        int[] A,B,C;

        public CustomThread1(int id, int n, int p, int[] a, int[] b, int[] c) {
            this.id = id;
            this.n = n;
            this.p = p;
            this.A = a;
            this.B = b;
            this.C = c;
        }

        public void run(){
            System.out.println("Hey eu sunt thredul cu id " + id);
            for(int i =id; i<n; i=i+p){
                C[i] = A[i] +B[i];
            }
        }
    }
}