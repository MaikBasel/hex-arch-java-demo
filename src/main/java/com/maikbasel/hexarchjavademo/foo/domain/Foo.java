package com.maikbasel.hexarchjavademo.foo.domain;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Foo {

    @Getter
    private Long id;
    @Getter
    private final String name;

    public static Foo withoutId(String name) {
        return new Foo(null, name);
    }

    public static Foo withId(Long id, String name) {
        return new Foo(id, name);
    }
}
