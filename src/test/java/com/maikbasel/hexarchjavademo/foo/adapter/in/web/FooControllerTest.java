package com.maikbasel.hexarchjavademo.foo.adapter.in.web;

import com.leakyabstractions.result.Results;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooCommand;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooUseCase;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import com.maikbasel.hexarchjavademo.foo.domain.FooCreationFailure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {FooController.class})
class FooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateFooUseCase createFooUseCase;

    @TestConfiguration
    static class FooTestConfiguration {
        @Bean
        public FooWebMapper fooMapper() {
            return new FooWebMapper();
        }
    }

    @Test
    void givenValidRequest_whenCreatingFoo_thenShouldRespondWithCreationID() throws Exception {
        var requestBody = """
                {
                  "name": "test"
                }
                """;
        var expectedCommand = new CreateFooCommand("test");
        var outPutUuid = UUID.randomUUID();
        var outputFoo = Foo.withId(1L, "name", outPutUuid, LocalDateTime.now());
        when(createFooUseCase.createFoo(any())).thenReturn(Results.success(outputFoo));

        mockMvc.perform(post("/foo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/foo/%s".formatted(outPutUuid.toString())));

        then(createFooUseCase).should().createFoo(expectedCommand);
    }

    @Test
    void givenFooNameIsAlreadyTaken_whenCreatingFoo_thenShouldRespondWithError() throws Exception {
        var requestBody = """
                {
                  "name": "test"
                }
                """;
        when(createFooUseCase.createFoo(any())).thenReturn(Results.failure(FooCreationFailure.NAME_ALREADY_TAKEN));

        mockMvc.perform(post("/foo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Name 'test' is already taken."));
    }
}