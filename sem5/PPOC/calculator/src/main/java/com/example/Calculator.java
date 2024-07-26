package com.example;

public class Calculator {

    //here is the constructor
    public Calculator() {
    }

    public double calculate(Operation op, double nr1, double nr2){
        return op.execute(nr1, nr2);
    }
}
