package com.maikbasel.hexarchjavademo.foo.adapter.out.bar;

import com.maikbasel.hexarchjavademo.bar.application.port.driving.CreateBarWhenFooIsCreatedUseCase;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.BarGatewayPort;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BarGatewayAdapter implements BarGatewayPort {

    private final FooEventMapper fooEventMapper;
    private final CreateBarWhenFooIsCreatedUseCase createBarWhenFooIsCreatedUseCase;

    @Override
    public void createBar(Foo foo) {
        var createdEvent = fooEventMapper.toFooCreatedDomainEvent(foo);

        createBarWhenFooIsCreatedUseCase.createBar(createdEvent);
    }
}
