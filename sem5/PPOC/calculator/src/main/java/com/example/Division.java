package com.example;

public class Division implements Operation {

    //this is the constructor
    public Division() {
    }

    //actual implementation of the operation
    @Override
    public double execute(double nr1, double nr2) {
        return nr1 / nr2;
    }
}
