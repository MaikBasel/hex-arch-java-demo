package com.maikbasel.hexarchjavademo.foo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.UUID;

class FooTest {

    @Test
    void givenTimeIsAfterNinePm_whenCreatingNewFoo_thenShouldReturnFoo() {
        var date = LocalDate.of(2023, Month.JANUARY, 1);
        var time = LocalTime.of(22, 0);
        var inputDateTime = LocalDateTime.of(date, time);
        var inputUuid = UUID.fromString("d02ed511-be2e-4dc9-9dfe-89994eb04932");
        var expected = new Foo(null, "test", inputUuid, inputDateTime);

        var actual = Foo.withoutId("test", inputUuid, inputDateTime);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenTimeIsBeforeNinePm_whenCreatingNewFoo_thenShouldThrowException() {
        var date = LocalDate.of(2023, Month.JANUARY, 1);
        var time = LocalTime.of(1, 0);
        var inputDateTime = LocalDateTime.of(date, time);
        var inputUuid = UUID.fromString("d02ed511-be2e-4dc9-9dfe-89994eb04932");

        var thrown = Assertions.catchException(() -> Foo.withoutId("test", inputUuid, inputDateTime));

        Assertions.assertThat(thrown)
                .isInstanceOf(IllegalFooCreationTime.class)
                .hasMessage("Foo must not be created before 9 pm!");
    }
}