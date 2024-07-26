package com.example;

public class Multiplication implements Operation {

    //this is the constructor
    public Multiplication() {
    }

    //actual implementation of the operation
    @Override
    public double execute(double nr1, double nr2) {
        return nr1 * nr2;
    }
}
