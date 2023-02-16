package com.maikbasel.hexarchjavademo.bar.application.port.driving;

import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import com.maikbasel.hexarchjavademo.common.FooCreatedDomainEvent;

public interface CreateBarWhenFooIsCreatedUseCase {

    Bar createBar(FooCreatedDomainEvent event);
}
