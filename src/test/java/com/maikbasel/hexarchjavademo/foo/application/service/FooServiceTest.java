package com.maikbasel.hexarchjavademo.foo.application.service;

import com.leakyabstractions.result.Result;
import com.leakyabstractions.result.Results;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.BarGatewayPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.LoadFooPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.SaveFooPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooCommand;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.FooCreationProperties;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreationFailure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FooServiceTest {

    private static final UUID INPUT_UUID = UUID.fromString("d02ed511-be2e-4dc9-9dfe-89994eb04932");

    @Mock
    private LoadFooPort loadFooPort;

    @Mock
    private SaveFooPort saveFooPort;

    @Mock
    private BarGatewayPort barGatewayPort;

    @Spy
    @SuppressWarnings("unused")
    // Required dependency of FooService. Currently, there is no other way to inject "real" objects, when using
    // @InjectMocks, but not using @InjectMocks would make the tests more brittle by tying it directly to the FooService
    // constructor.
    private FooCreationProperties fooCreationProperties = new FooCreationProperties(
            "Test_",
            () -> INPUT_UUID,
            Clock.fixed(Instant.parse("2023-01-01T21:00:00Z"), ZoneOffset.UTC)
    );

    @InjectMocks
    private FooService fooService;

    @Nested
    class WhenCreatingFooIsSuccessful {

        private CreateFooCommand input;
        private Foo newFoo;
        private Foo savedFoo;


        @BeforeEach
        void setUp() {
            input = new CreateFooCommand("test");
            var dateTime = LocalDateTime.parse("2023-01-01T21:00:00");
            newFoo = Foo.withoutId("Test_test", INPUT_UUID, dateTime);
            savedFoo = Foo.withId(1L, "Test_test", INPUT_UUID, dateTime);
            Mockito.when(saveFooPort.saveFoo(any())).thenReturn(savedFoo);
        }

        @Test
        void thenShouldSaveFoo() {
            var expected = Results.success(savedFoo);

            Result<Foo, FooCreationFailure> actual = fooService.createFoo(input);

            Assertions.assertThat(actual).isEqualTo(expected);
            Mockito.verify(saveFooPort).saveFoo(newFoo);
        }

        @Test
        void thenShouldCreateBar() {
            fooService.createFoo(input);

            Mockito.verify(barGatewayPort).createBar(savedFoo);
        }
    }

    @Nested
    class WhenFooNameIsAlreadyTaken {

        @Test
        void thenShouldReturnFailure() {
            var input = new CreateFooCommand("test");
            Mockito.when(loadFooPort.isNameAlreadyTaken("test")).thenReturn(true);

            Result<Foo, FooCreationFailure> actual = fooService.createFoo(input);

            Assertions.assertThat(actual).isEqualTo(Results.failure(FooCreationFailure.NAME_ALREADY_TAKEN));
        }
    }
}