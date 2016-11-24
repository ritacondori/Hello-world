package com.aiconoa.sakila.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiconoa.sakila.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
