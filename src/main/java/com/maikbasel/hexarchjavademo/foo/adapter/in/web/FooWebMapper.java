package com.maikbasel.hexarchjavademo.foo.adapter.in.web;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.springframework.stereotype.Component;

@Component
class FooWebMapper {
    public Foo toFoo(CreateFooRequest createFooRequest) {
        return Foo.withoutId(createFooRequest.getName());
    }
}
