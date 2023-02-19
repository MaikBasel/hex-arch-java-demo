package com.maikbasel.hexarchjavademo.foo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Foo {

    @Getter
    private final Long id;
    @With
    @Getter
    private final String name;
    @With
    @Getter
    private final UUID uuid;
    @With
    @Getter
    private LocalDateTime time;

    public static Foo withoutId(String name, UUID uuid, LocalDateTime time) {
        if (time.toLocalTime().isBefore(LocalTime.of(21, 0))) {
            throw new IllegalFooCreationTime("Foo must not be created before 9 pm!");
        }

        return new Foo(null, name, uuid, time);
    }

    public static Foo withId(Long id, String name, UUID uuid, LocalDateTime time) {
        return new Foo(id, name, uuid, time);
    }
}
