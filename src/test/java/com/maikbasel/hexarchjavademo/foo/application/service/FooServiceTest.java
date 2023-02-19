package com.maikbasel.hexarchjavademo.foo.application.service;

import com.maikbasel.hexarchjavademo.foo.application.port.driven.BarGatewayPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.SaveFooPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.FooCreationProperties;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FooServiceTest {

    @Mock
    private SaveFooPort saveFooPort;

    @Mock
    private BarGatewayPort barGatewayPort;

    @Spy
    @SuppressWarnings("unused")
    // Required dependency of FooService. Currently, there is no other way to inject "real" objects, when using
    // @InjectMocks, but not using @InjectMocks would make the tests more brittle by tying it directly to the FooService
    // constructor.
    private FooCreationProperties fooCreationProperties = new FooCreationProperties("Test_");

    @InjectMocks
    private FooService fooService;

    @Nested
    class WhenCreatingFoo {

        private Foo input;
        private Foo modified;
        private Foo output;

        @BeforeEach
        void setUp() {
            input = Foo.withoutId("test");
            modified = Foo.withoutId("Test_test");
            output = Foo.withId(1L, "Test_test");
            Mockito.when(saveFooPort.saveFoo(any())).thenReturn(output);
        }

        @Test
        void thenShouldSaveFoo() {
            var actual = fooService.createFoo(input);

            Assertions.assertThat(actual).isEqualTo(output);
            Mockito.verify(saveFooPort).saveFoo(modified);
        }

        @Test
        void thenShouldCreateBar() {
            fooService.createFoo(input);

            Mockito.verify(barGatewayPort).createBar(output);
        }
    }
}