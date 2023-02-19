package com.maikbasel.hexarchjavademo.bar.adapter.out.persistence;

import com.maikbasel.hexarchjavademo.bar.application.port.driven.SaveBarPort;
import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BarPersistenceAdapter implements SaveBarPort {

    private final BarPersistenceMapper barPersistenceMapper;
    private final BarDao barDao;

    @Override
    public Bar saveBar(Bar bar) {
        BarEntity newBar = barPersistenceMapper.toBarEntity(bar);

        var persistedBar = barDao.save(newBar);

        return barPersistenceMapper.toBar(persistedBar);
    }
}
