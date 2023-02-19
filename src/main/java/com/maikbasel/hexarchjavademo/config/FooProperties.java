package com.maikbasel.hexarchjavademo.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Value
@ConfigurationProperties(prefix = "foo")
class FooProperties {

    String prefix;

    @ConstructorBinding
    public FooProperties(String prefix) {
        this.prefix = prefix;
    }
}
