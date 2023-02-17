package com.maikbasel.hexarchjavademo.bar.adapter.driven.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface BarDao extends JpaRepository<BarEntity, Long> {
}
