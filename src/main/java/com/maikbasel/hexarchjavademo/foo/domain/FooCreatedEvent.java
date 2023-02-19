package com.maikbasel.hexarchjavademo.foo.domain;

import lombok.Value;

@Value
public class FooCreatedEvent {
    Long id;
    String name;
}
