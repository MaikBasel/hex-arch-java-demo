package com.maikbasel.hexarchjavademo.bar.adapter.out.persistence;

import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import org.springframework.stereotype.Component;

@Component
class BarPersistenceMapper {

    public Bar toBar(BarEntity barEntity) {
        return Bar.withId(
                barEntity.getId(),
                barEntity.getFooName()
        );
    }

    public BarEntity toBarEntity(Bar bar) {
        var newBar = new BarEntity();
        newBar.setFooName(bar.getFooName());

        return newBar;
    }
}
