package com.aiconoa.sakila.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.aiconoa.sakila.entity.Film;
import com.aiconoa.sakila.repository.FilmRepository;

@Service
public class SakilaService {
	
	@Inject
	private FilmRepository filmRepository;

	public List<Film> findAllFilms() {
		return filmRepository.findAll();
	}

	public Film find(Integer id) {
		return filmRepository.findOne(id);
	}

}
