package com.maikbasel.hexarchjavademo.bar.adapter.driven.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
class BarEntity {
    @Id
    private Long id;

    private String name;
}
