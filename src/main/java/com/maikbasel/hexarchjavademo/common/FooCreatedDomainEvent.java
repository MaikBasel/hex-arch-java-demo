package com.maikbasel.hexarchjavademo.common;

import lombok.Value;

@Value
public class FooCreatedDomainEvent {
    Long id;
    String name;
}
