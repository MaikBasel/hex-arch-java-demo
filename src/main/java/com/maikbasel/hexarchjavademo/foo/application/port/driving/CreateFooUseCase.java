package com.maikbasel.hexarchjavademo.foo.application.port.driving;

import com.leakyabstractions.result.Result;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreationFailure;

public interface CreateFooUseCase {

    Result<Foo, FooCreationFailure> createFoo(CreateFooCommand fooCommand);
}
