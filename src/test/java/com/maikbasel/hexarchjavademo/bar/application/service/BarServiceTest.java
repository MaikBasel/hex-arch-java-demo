package com.maikbasel.hexarchjavademo.bar.application.service;

import com.maikbasel.hexarchjavademo.bar.application.port.driven.SaveBarPort;
import com.maikbasel.hexarchjavademo.bar.domain.Bar;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreatedEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BarServiceTest {

    @Mock
    private SaveBarPort saveBarPort;

    @InjectMocks
    private BarService barService;

    @Test
    void shouldSaveBar() {
        var input = new FooCreatedEvent(1L, "testFoo");
        var inputBar = Bar.withoutId("testFoo");
        var output = Bar.withId(1L, "testFoo");
        Mockito.when(saveBarPort.saveBar(any())).thenReturn(output);

        var actual = barService.createBar(input);

        Assertions.assertThat(actual).isEqualTo(output);
        Mockito.verify(saveBarPort).saveBar(inputBar);
    }
}