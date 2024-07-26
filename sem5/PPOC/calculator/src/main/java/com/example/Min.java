package com.example;

public class Min implements Operation{

    //this is the constructor
    public Min() {
    }

    //the actual implementation
    @Override
    public double execute(double nr1, double nr2) {
        return Math.min(nr1, nr2);
    }
}
