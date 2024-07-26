package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.println("Type the operation you want (+, -, *, /, min, max, sqrt) or 'exit' if you are done:");
            String operation = scanner.nextLine();

            if (operation.equals("exit")) {
                break;
            }

            Operation operation1 = getOperation(operation);
            if (operation1 != null) {
                System.out.println("Type 2 numbers:");
                double num1 = scanner.nextDouble();
                double num2 = scanner.nextDouble();
                scanner.nextLine(); // Check newline

                double result = calculator.calculate(operation1, num1, num2);
                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid operation. Please try again.");
            }
        }
    }

    private static Operation getOperation(String operation) {
        switch (operation) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "*":
                return new Multiplication();
            case "/":
                return new Division();
            case "min":
                return new Min();
            case "max":
                return new Max();
            case "sqrt":
                return new SquareRoot();
            default:
                return null;
        }
    }
}