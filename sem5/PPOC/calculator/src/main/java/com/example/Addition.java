package com.example;

// this is the Sum Class
public class Addition implements Operation {

    // this is the constructor
    public Addition() {
    }

    //the actual implementation for this operation
    @Override
    public double execute(double nr1, double nr2) {
        return nr1 + nr2;
    }
}
