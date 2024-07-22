package org.example.service;

public class CompetitionException extends Exception{
    public CompetitionException(){
    }

    public CompetitionException(String msg){
        super(msg);
    }

    public CompetitionException(String msg, Throwable cause){
        super(msg, cause);
    }
}
