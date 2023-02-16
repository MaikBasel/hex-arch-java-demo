package com.maikbasel.hexarchjavademo.foo.adapter.driven.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FooDao extends JpaRepository<FooEntity, Long> {
}
