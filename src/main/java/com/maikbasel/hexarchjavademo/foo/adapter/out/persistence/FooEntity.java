package com.maikbasel.hexarchjavademo.foo.adapter.driven.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
class FooEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
