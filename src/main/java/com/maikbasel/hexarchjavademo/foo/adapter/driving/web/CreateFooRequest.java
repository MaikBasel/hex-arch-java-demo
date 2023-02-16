package com.maikbasel.hexarchjavademo.foo.adapter.driving.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class CreateFooRequest {

    String name;

    @JsonCreator
    public CreateFooRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}
