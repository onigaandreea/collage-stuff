package com.example;

public class Subtraction implements Operation {

    //this is a constructor
    public Subtraction() {
    }

    //actual implementation of the operation
    @Override
    public double execute(double nr1, double nr2) {
        return nr1 - nr2;
    }
}
