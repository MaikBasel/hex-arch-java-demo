package com.maikbasel.hexarchjavademo.foo.domain;

public class IllegalFooCreationTime extends RuntimeException {

    public IllegalFooCreationTime(String message) {
        super(message);
    }
}
