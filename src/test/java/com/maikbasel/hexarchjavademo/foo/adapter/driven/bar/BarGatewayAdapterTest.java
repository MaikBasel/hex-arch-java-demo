package com.maikbasel.hexarchjavademo.foo.adapter.driven.bar;

import com.maikbasel.hexarchjavademo.bar.application.port.driving.CreateBarWhenFooIsCreatedUseCase;
import com.maikbasel.hexarchjavademo.common.FooCreatedDomainEvent;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BarGatewayAdapter.class, FooEventMapper.class})
class BarGatewayAdapterTest {

    @MockBean
    private CreateBarWhenFooIsCreatedUseCase createBarWhenFooIsCreatedUseCase;

    @Autowired
    private BarGatewayAdapter barGatewayAdapter;

    @Test
    void shouldCreateBar() {
        var input = Foo.withId(1L, "test");
        var output = new FooCreatedDomainEvent(input.getId(), input.getName());

        barGatewayAdapter.createBar(input);

        Mockito.verify(createBarWhenFooIsCreatedUseCase).createBar(output);
    }
}