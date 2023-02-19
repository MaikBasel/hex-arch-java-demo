package com.maikbasel.hexarchjavademo.foo.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
class FooEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String uuid;
    private LocalDateTime time;
}
