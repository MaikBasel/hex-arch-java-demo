package com.maikbasel.hexarchjavademo.foo.application.service;

import com.maikbasel.hexarchjavademo.foo.application.port.driven.BarGatewayPort;
import com.maikbasel.hexarchjavademo.foo.application.port.driven.SaveFooPort;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FooServiceTest {

    @Mock
    private SaveFooPort saveFooPort;

    @Mock
    private BarGatewayPort barGatewayPort;

    @InjectMocks
    private FooService fooService;

    @Nested
    class WhenCreatingFoo {

        private Foo input;
        private Foo output;

        @BeforeEach
        void setUp() {
            input = Foo.withoutId("test");
            output = Foo.withId(1L, "test");
            Mockito.when(saveFooPort.saveFoo(any())).thenReturn(output);
        }

        @Test
        void thenShouldSaveFoo() {
            var actual = fooService.createFoo(input);

            Assertions.assertThat(actual).isEqualTo(output);
            Mockito.verify(saveFooPort).saveFoo(input);
        }

        @Test
        void thenShouldCreateBar() {
            fooService.createFoo(input);

            Mockito.verify(barGatewayPort).createBar(output);
        }
    }
}