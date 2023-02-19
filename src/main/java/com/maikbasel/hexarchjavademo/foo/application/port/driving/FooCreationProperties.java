package com.maikbasel.hexarchjavademo.foo.application.port.driving;

import lombok.*;

import java.time.Clock;
import java.util.UUID;
import java.util.function.Supplier;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class FooCreationProperties {

    @Getter
    private final String prefix;
    @Getter
    private final Supplier<UUID> uuid;
    @Getter
    private final Clock clock;
}
