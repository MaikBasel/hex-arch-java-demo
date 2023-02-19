package com.maikbasel.hexarchjavademo.bar.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarDao extends JpaRepository<BarEntity, Long> {
}
