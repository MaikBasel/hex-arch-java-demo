package com.maikbasel.hexarchjavademo.bar.adapter.driven.persistence;

import com.maikbasel.hexarchjavademo.bar.application.port.driven.SaveBarPort;
import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BarPersistenceAdapter implements SaveBarPort {

    private final BarDao barDao;

    @Override
    public Bar saveBar(Bar bar) {
        throw new IllegalStateException("Not implemented yet");
    }
}
