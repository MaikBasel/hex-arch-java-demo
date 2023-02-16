package com.maikbasel.hexarchjavademo.bar.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bar {

    @Getter
    private BarId id;
    @Getter
    private final String name;

    public static Bar withId(BarId id, String name) {
        return new Bar(id, name);
    }

    public static Bar withoutId(String name) {
        return new Bar(null, name);
    }
}
