package com.maikbasel.hexarchjavademo.foo.application.port.driven;

import com.maikbasel.hexarchjavademo.foo.domain.Foo;

public interface BarGatewayPort {

    void createBar(Foo foo);
}
