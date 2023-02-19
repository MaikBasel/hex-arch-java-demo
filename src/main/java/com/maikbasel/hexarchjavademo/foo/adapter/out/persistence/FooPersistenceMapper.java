package com.maikbasel.hexarchjavademo.foo.adapter.out.persistence;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class FooPersistenceMapper {

    public Foo toFoo(FooEntity fooEntity) {
        var uuid = UUID.fromString(fooEntity.getUuid());
        return Foo.withId(
                fooEntity.getId(),
                fooEntity.getName(),
                uuid,
                fooEntity.getTime()
        );
    }

    public FooEntity toFooEntity(Foo foo) {
        var entity = new FooEntity();
        entity.setId(foo.getId());
        entity.setName(foo.getName());
        entity.setUuid(foo.getUuid().toString());
        entity.setTime(foo.getTime());

        return entity;
    }
}
