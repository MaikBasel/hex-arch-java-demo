package com.maikbasel.hexarchjavademo.foo.domain;

public class IllegalFooCreationTimeException extends RuntimeException {

    public IllegalFooCreationTimeException() {
        super("Foo must not be created before 9 pm!");
    }
}
