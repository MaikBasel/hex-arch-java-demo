package com.maikbasel.hexarchjavademo.foo.adapter.in.web;

import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooCommand;
import org.springframework.stereotype.Component;

@Component
class FooWebMapper {

    public CreateFooCommand toFooCommand(CreateFooRequest createFooRequest) {
        return new CreateFooCommand(createFooRequest.getName());
    }
}
