package com.maikbasel.hexarchjavademo.foo.adapter.in.web;

import com.maikbasel.hexarchjavademo.foo.application.port.driving.CreateFooUseCase;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
        when(createFooUseCase.createFoo(any())).thenReturn(Foo.withId(1L, "name"));

        mockMvc.perform(post("/foo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/foo/%s".formatted(1L)));

        then(createFooUseCase).should().createFoo(Foo.withoutId("test"));
    }
}