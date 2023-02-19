package com.maikbasel.hexarchjavademo.foo.adapter.in.web;

import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooUseCase;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreationFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/foo")
@RequiredArgsConstructor
class FooController {

    private final CreateFooUseCase createFooUseCase;
    private final FooWebMapper fooMapper;

    @PostMapping
    public ResponseEntity<FooErrorResponse> createFoo(
            UriComponentsBuilder uriComponentsBuilder,
            @RequestBody CreateFooRequest request
    ) {
        var result = createFooUseCase.createFoo(fooMapper.toFooCommand(request));
        if (result.hasSuccess()) {
            var foo = result.getSuccess().orElseThrow();
            var uriComponents =
                    uriComponentsBuilder.path("/foo/{id}").buildAndExpand(foo.getUuid());
            return ResponseEntity.created(uriComponents.toUri()).build();
        } else {
            FooCreationFailure failure = result.getFailure().orElseThrow();
            var fooErrorResponse  = new FooErrorResponse(failure.getMessageTemplate().formatted(request.getName()));
            return ResponseEntity.badRequest().body(fooErrorResponse);
        }
    }
}
