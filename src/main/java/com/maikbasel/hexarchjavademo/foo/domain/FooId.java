package com.maikbasel.hexarchjavademo.foo.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class FooId {

    String value;

    public FooId(UUID value) {
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return value;
    }
}
