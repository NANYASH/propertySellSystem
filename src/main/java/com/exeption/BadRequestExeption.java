package com.exeption;


public class BadRequestExeption extends Exception{
    public BadRequestExeption(String cause) {
        super(cause);
    }
}
