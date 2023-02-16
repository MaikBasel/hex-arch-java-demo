package com.maikbasel.hexarchjavademo.bar.application.service;

import com.maikbasel.hexarchjavademo.bar.application.port.driven.SaveBarPort;
import com.maikbasel.hexarchjavademo.bar.application.port.driving.CreateBarWhenFooIsCreatedUseCase;
import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import com.maikbasel.hexarchjavademo.common.FooCreatedDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BarService implements CreateBarWhenFooIsCreatedUseCase {

    private final SaveBarPort saveBarPort;

    @Override
    public Bar createBar(FooCreatedDomainEvent event) {
        throw new IllegalStateException("Not yet implemented");
    }
}
