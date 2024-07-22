package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class generareMatrici {
    public static void main(String[] args) {
        int rows = 1000; // Numărul de rânduri
        int columns = 1000; // Numărul de coloane
        int[][] matrix = generateRandomMatrix(rows, columns);

        // Afișăm matricea generată
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("matrice.txt"))) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + " "); // Scrieți fiecare element al matricei, separat de spațiu
                }
                writer.newLine(); // Treci la următorul rând în fișier
            }

//            writer.write(0 + " ");
//            writer.write(1 + " ");
//            writer.write(-1 + " ");
//            writer.newLine();
//            writer.write(1 + " ");
//            writer.write(0 + " ");
//            writer.write(-1 + " ");
//            writer.newLine();
//            writer.write(-1 + " ");
//            writer.write(1 + " ");
//            writer.write(0 + " ");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] generateRandomMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(10) - 1; // Generează un număr întreg aleatoriu între 1 și 10
            }
        }

        return matrix;
    }
}
