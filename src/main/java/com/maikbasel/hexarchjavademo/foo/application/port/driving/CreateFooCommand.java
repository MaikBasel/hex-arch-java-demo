package com.maikbasel.hexarchjavademo.foo.application.port.driving;

import com.maikbasel.hexarchjavademo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateFooCommand extends SelfValidating<CreateFooCommand> {

    @NotBlank
    String name;

    public CreateFooCommand(String name) {
        this.name = name;

        validateSelf();
    }
}
