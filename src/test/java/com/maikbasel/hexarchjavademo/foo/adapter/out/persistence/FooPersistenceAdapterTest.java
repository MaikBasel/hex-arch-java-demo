package com.maikbasel.hexarchjavademo.foo.adapter.out.persistence;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.UUID;

@Import({FooPersistenceAdapter.class, FooPersistenceMapper.class})
@DataJpaTest
class FooPersistenceAdapterTest {

    @Autowired
    private FooPersistenceAdapter fooPersistenceAdapter;

    @Autowired
    private FooDao fooDao;

    @Test
    void shouldPersistFoo() {
        var date = LocalDate.of(2023, Month.JANUARY, 1);
        var time = LocalTime.of(22, 0);
        var inputDateTime = LocalDateTime.of(date, time);
        var inputUuid = UUID.fromString("d02ed511-be2e-4dc9-9dfe-89994eb04932");
        var input = Foo.withoutId("test", inputUuid, inputDateTime);
        var expected = new FooEntity();
        expected.setId(1L);
        expected.setName("test");
        expected.setUuid(inputUuid.toString());
        expected.setTime(inputDateTime);

        fooPersistenceAdapter.saveFoo(input);

        Assertions.assertThat(fooDao.findById(1L))
                .isPresent()
                .contains(expected);
    }

    @Test
    void givenNoFooWithNameAlreadyExists_thenShouldReturnFalse() {
        var actual = fooPersistenceAdapter.isNameAlreadyTaken("test");

        Assertions.assertThat(actual).isFalse();
    }

    @Test
    void givenFooWithNameAlreadyExists_thenShouldReturnFalse() {
        var date = LocalDate.of(2023, Month.JANUARY, 1);
        var time = LocalTime.of(22, 0);
        var inputDateTime = LocalDateTime.of(date, time);
        var inputUuid = UUID.fromString("d02ed511-be2e-4dc9-9dfe-89994eb04932");
        var input = new FooEntity();
        input.setId(1L);
        input.setName("test");
        input.setUuid(inputUuid.toString());
        input.setTime(inputDateTime);
        fooDao.save(input);

        var actual = fooPersistenceAdapter.isNameAlreadyTaken("test");

        Assertions.assertThat(actual).isTrue();
    }
}