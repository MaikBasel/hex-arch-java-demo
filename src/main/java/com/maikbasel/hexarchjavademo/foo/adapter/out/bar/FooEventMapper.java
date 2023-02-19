package com.maikbasel.hexarchjavademo.foo.adapter.out.bar;

import com.maikbasel.hexarchjavademo.foo.domain.FooCreatedEvent;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.springframework.stereotype.Component;

@Component
class FooEventMapper {

    public FooCreatedEvent toFooCreatedDomainEvent(Foo foo) {
        return new FooCreatedEvent(foo.getId(), foo.getName());
    }
}
