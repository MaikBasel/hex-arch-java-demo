package com.maikbasel.hexarchjavademo.foo.adapter.in.web;

import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooUseCase;
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
    public ResponseEntity<Void> createFoo(
            UriComponentsBuilder uriComponentsBuilder,
            @RequestBody CreateFooRequest request
    ) {
        var foo = createFooUseCase.createFoo(fooMapper.toFoo(request));

        var uriComponents =
                uriComponentsBuilder.path("/foo/{id}").buildAndExpand(foo.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
