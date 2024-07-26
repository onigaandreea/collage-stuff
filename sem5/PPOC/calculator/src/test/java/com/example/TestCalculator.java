package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestCalculator {
    public Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void testAddition(){
        Operation addition = new Addition();
        double result = calculator.calculate(addition, 28, 46);
        Assertions.assertEquals(74, result, 0.001); //Verify if the result is the one expected
        result = calculator.calculate(addition, -28, 46);
        Assertions.assertEquals(18, result, 0.001);
        result = calculator.calculate(addition, -8.4, -36.6);
        Assertions.assertEquals(-45.0, result, 0.001);
    }

    @Test
    public void testSubtraction(){
        Operation sub = new Subtraction();
        double result = calculator.calculate(sub, 28, 4);
        Assertions.assertEquals(24, result, 0.001); //Verify if the result is the one expected
        result = calculator.calculate(sub, -28, 4);
        Assertions.assertEquals(-32, result, 0.001);
        result = calculator.calculate(sub, -28.4, -4.4);
        Assertions.assertEquals(-24.0, result, 0.001);
    }

    @Test
    public void testMultiplication(){
        Operation mul = new Multiplication();
        double result = calculator.calculate(mul, 10, 12);
        Assertions.assertEquals(120, result, 0.001); //Verify if the result is the one expected
        result = calculator.calculate(mul, -10.6, 12.2);
        Assertions.assertEquals(-129.32, result, 0.001);
        result = calculator.calculate(mul, -10, -12);
        Assertions.assertEquals(120, result, 0.001);
    }

    @Test
    public void testDivision(){
        Operation div = new Division();
        double result = calculator.calculate(div, 28, 4);
        Assertions.assertEquals(7, result, 0.001); //Verify if the result is the one expected
        result = calculator.calculate(div, -28, 4);
        Assertions.assertEquals(-7, result, 0.001);
        result = calculator.calculate(div, -28, -0.5);
        Assertions.assertEquals(56, result, 0.001);
    }

    @Test
    public void testMin(){
        Operation min = new Min();
        double result = calculator.calculate(min, 28, -46);
        Assertions.assertEquals(-46, result, 0.001); //Verify if the result is the one expected
        result = calculator.calculate(min, -28.3, -28.33);
        Assertions.assertEquals(-28.33, result, 0.001);
    }

    @Test
    public void testMax(){
        Operation max = new Max();
        double result = calculator.calculate(max, 28, 46);
        Assertions.assertEquals(46, result, 0.001); //Verify if the result is the one expected
        result = calculator.calculate(max, -28, -6.8);
        Assertions.assertEquals(-6.8, result, 0.001);
    }

    @Test
    public void testSqrt(){
        Operation sr = new SquareRoot();
        double result = calculator.calculate(sr, 64, -18);
        Assertions.assertEquals(8, result, 0.001); //Verify if the result is the one expected

        //verify if the second number is positive
        result = calculator.calculate(sr, -64, 16);
        Assertions.assertEquals(4, result, 0.001);

        // Verify if the operation is throwing an exception if both numbers are negative
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(sr, -12.45, -20.3);
        });
    }
}
