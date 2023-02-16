package com.maikbasel.hexarchjavademo.foo.application.service;

import com.maikbasel.hexarchjavademo.foo.application.port.driven.BarGatewayPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.SaveFooPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooUseCase;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class FooService implements CreateFooUseCase {

    private final SaveFooPort saveFooPort;
    private final BarGatewayPort barGatewayPort;

    @Override
    public Foo createFoo(Foo foo) {
        var persistedFoo = saveFooPort.saveFoo(foo);

        barGatewayPort.createBar(persistedFoo);

        return persistedFoo;
    }
}
