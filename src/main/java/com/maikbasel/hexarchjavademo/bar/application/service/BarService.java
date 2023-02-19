package com.maikbasel.hexarchjavademo.bar.application.service;

import com.maikbasel.hexarchjavademo.bar.application.port.driven.SaveBarPort;
import com.maikbasel.hexarchjavademo.bar.application.port.driving.CreateBarWhenFooIsCreatedUseCase;
import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BarService implements CreateBarWhenFooIsCreatedUseCase {

    private final SaveBarPort saveBarPort;

    @Override
    public Bar createBar(FooCreatedEvent event) {
        var bar = Bar.withoutId(event.getName());

        return saveBarPort.saveBar(bar);
    }
}
