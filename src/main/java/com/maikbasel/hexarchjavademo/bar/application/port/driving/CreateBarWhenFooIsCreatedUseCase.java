package com.maikbasel.hexarchjavademo.bar.application.port.driving;

import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreatedEvent;

public interface CreateBarWhenFooIsCreatedUseCase {

    Bar createBar(FooCreatedEvent event);
}
