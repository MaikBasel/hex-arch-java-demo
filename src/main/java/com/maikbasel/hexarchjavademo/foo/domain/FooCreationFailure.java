package com.maikbasel.hexarchjavademo.foo.domain;

import lombok.Getter;

public enum FooCreationFailure {

    NAME_ALREADY_TAKEN("Name '%s' is already taken.");

    @Getter
    private final String messageTemplate;

    FooCreationFailure(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }
}
