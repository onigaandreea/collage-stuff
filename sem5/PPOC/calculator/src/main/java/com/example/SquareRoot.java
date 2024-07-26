package com.example;

public class SquareRoot implements Operation{

    //this is the constructor
    public SquareRoot() {
    }

    //the actual implementation of the operation
    @Override
    public double execute(double nr1, double nr2) {
        if(nr1>=0)
            return Math.sqrt(nr1);
        else if (nr2 >= 0)
            return Math.sqrt(nr2);
        else
            throw new IllegalArgumentException("there is no positive number");

    }
}
