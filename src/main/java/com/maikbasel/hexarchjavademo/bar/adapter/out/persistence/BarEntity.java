package com.maikbasel.hexarchjavademo.bar.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
class BarEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String fooName;
}
