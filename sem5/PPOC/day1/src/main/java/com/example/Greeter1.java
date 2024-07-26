package com.example;

/**
 * This is a class.
 */
public class Greeter1 {

    /**
     * This is a constructor.
     */
    public Greeter1() {
    }

    //TODO: Add javadoc comment
    public String greet(String someone) {
        try {
            return String.format("Hello, %s!", someone);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
