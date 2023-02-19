package com.maikbasel.hexarchjavademo.foo.application.service;

import com.leakyabstractions.result.Result;
import com.leakyabstractions.result.Results;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.BarGatewayPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.LoadFooPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.SaveFooPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooCommand;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooUseCase;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.FooCreationProperties;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreationFailure;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class FooService implements CreateFooUseCase {

    private final FooCreationProperties fooCreationProperties;
    private final LoadFooPort loadFooPort;
    private final SaveFooPort saveFooPort;
    private final BarGatewayPort barGatewayPort;

    @Override
    public Result<Foo, FooCreationFailure> createFoo(CreateFooCommand command) {
        if (loadFooPort.isNameAlreadyTaken(command.getName())) {
            return Results.failure(FooCreationFailure.NAME_ALREADY_TAKEN);
        }

        var fooName = fooCreationProperties.getPrefix() + command.getName();
        UUID fooUuid = fooCreationProperties.getUuid().get();
        LocalDateTime creationTime = LocalDateTime.now(fooCreationProperties.getClock());
        var newFoo = Foo.withoutId(fooName, fooUuid, creationTime);

        var persistedFoo = saveFooPort.saveFoo(newFoo);

        barGatewayPort.createBar(persistedFoo);

        return Results.success(persistedFoo);
    }
}
