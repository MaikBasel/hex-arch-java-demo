package com.maikbasel.hexarchjavademo.config;

import com.maikbasel.hexarchjavademo.foo.application.port.driving.FooCreationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FooProperties.class)
class FooConfig {

    @Bean
    FooCreationProperties fooCreationProperties(FooProperties fooProperties) {
        return new FooCreationProperties(fooProperties.getPrefix());
    }
}
