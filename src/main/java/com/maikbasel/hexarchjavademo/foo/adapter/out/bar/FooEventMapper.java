package com.maikbasel.hexarchjavademo.foo.adapter.out.bar;

import com.maikbasel.hexarchjavademo.common.FooCreatedDomainEvent;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.springframework.stereotype.Component;

@Component
class FooEventMapper {

    public FooCreatedDomainEvent toFooCreatedDomainEvent(Foo foo) {
        return new FooCreatedDomainEvent(foo.getId(), foo.getName());
    }
}
