package com.maikbasel.hexarchjavademo.foo.adapter.driven.persistence;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.springframework.stereotype.Component;

@Component
class FooPersistenceMapper {

    public Foo toFoo(FooEntity fooEntity) {
        return Foo.withId(fooEntity.getId(), fooEntity.getName());
    }

    public FooEntity toFooEntity(Foo foo) {
        var entity = new FooEntity();
        entity.setId(foo.getId());
        entity.setName(foo.getName());

        return entity;
    }
}
