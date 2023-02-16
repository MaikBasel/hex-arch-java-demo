package com.maikbasel.hexarchjavademo.foo.adapter.driven.persistence;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({FooPersistenceAdapter.class, FooPersistenceMapper.class})
@DataJpaTest
class FooPersistenceAdapterTest {

    @Autowired
    private FooPersistenceAdapter fooPersistenceAdapter;

    @Autowired
    private FooDao fooDao;

    @Test
    void shouldPersistFoo() {
        var input = Foo.withoutId("test");
        var expected = new FooEntity();
        expected.setId(1L);
        expected.setName("test");

        fooPersistenceAdapter.saveFoo(input);

        Assertions.assertThat(fooDao.findById(1L))
                .isPresent()
                .contains(expected);
    }
}