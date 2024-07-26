package com.example;

public class Max implements Operation{

    //this is the constructor
    public Max() {
    }

    //the actual implementation
    @Override
    public double execute(double nr1, double nr2) {
        return Math.max(nr1, nr2);
    }
}
