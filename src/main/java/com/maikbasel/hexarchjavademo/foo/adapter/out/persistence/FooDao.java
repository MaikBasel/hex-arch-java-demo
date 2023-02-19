package com.maikbasel.hexarchjavademo.foo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FooDao extends JpaRepository<FooEntity, Long> {
}
