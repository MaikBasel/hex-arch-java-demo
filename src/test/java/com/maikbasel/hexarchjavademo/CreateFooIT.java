package com.maikbasel.hexarchjavademo;

import com.maikbasel.hexarchjavademo.bar.adapter.out.persistence.BarDao;
import com.maikbasel.hexarchjavademo.foo.adapter.out.persistence.FooDao;
import com.maikbasel.hexarchjavademo.foo.application.port.driving.FooCreationProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.main.allow-bean-definition-overriding=true"}
)
class CreateFooIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FooDao fooDao;

    @Autowired
    private BarDao barDao;

    @TestConfiguration
    static class FooTestConfig {
        @Bean
        public FooCreationProperties fooCreationProperties() {
            return new FooCreationProperties(
                    "Cool_",
                    () -> UUID.fromString("d02ed511-be2e-4dc9-9dfe-89994eb04932"),
                    Clock.fixed(Instant.parse("2023-01-01T21:00:00Z"), ZoneOffset.UTC)
            );
        }
    }

    @Test
    void givenNoFooWithNameAlreadyExists_thenShouldCreateFooAndBar() {
        var requestBody = """
                {
                  "name": "test"
                }
                """;
        var requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(requestBody, requestHeaders);

        var actualLocation = restTemplate.postForLocation("/foo", request);
        var actualFoo = fooDao.findById(1L);
        var actualBar = barDao.findById(1L);

        Assertions.assertThat(actualLocation)
                .hasPath("/foo/d02ed511-be2e-4dc9-9dfe-89994eb04932");
        Assertions.assertThat(actualFoo)
                .isPresent();
        Assertions.assertThat(actualBar)
                .isPresent();
    }
}
