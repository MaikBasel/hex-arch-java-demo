package com.maikbasel.hexarchjavademo.foo.application.port.driving;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;

public interface CreateFooUseCase {

    Foo createFoo(Foo foo);
}
