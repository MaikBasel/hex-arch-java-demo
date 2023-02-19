package com.maikbasel.hexarchjavademo.bar.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bar {

    @Getter
    private Long id;
    @Getter
    private final String fooName;

    public static Bar withId(Long id, String fooName) {
        return new Bar(id, fooName);
    }

    public static Bar withoutId(String fooName) {
        return new Bar(null, fooName);
    }
}
