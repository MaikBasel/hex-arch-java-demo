package com.maikbasel.hexarchjavademo.foo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooDao extends JpaRepository<FooEntity, Long> {

    boolean existsByName(String name);
}
