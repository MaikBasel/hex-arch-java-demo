package com.maikbasel.hexarchjavademo.bar.adapter.out.persistence;

import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({BarPersistenceAdapter.class, BarPersistenceMapper.class})
@DataJpaTest
class BarPersistenceAdapterTest {

    @Autowired
    private BarPersistenceAdapter barPersistenceAdapter;

    @Autowired
    private BarDao barDao;

    @Test
    void shouldPersistBar() {
        var input = Bar.withoutId("testFoo");
        var expected = new BarEntity();
        expected.setId(1L);
        expected.setFooName("testFoo");

        barPersistenceAdapter.saveBar(input);

        Assertions.assertThat(barDao.findById(1L))
                .isPresent()
                .contains(expected);
    }
}