package com.aiconoa.sakila.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aiconoa.sakila.entity.Film;
import com.aiconoa.sakila.service.SakilaService;

@RestController
@RequestMapping("/api/films")
public class FilmRestController {

	private static final Logger LOGGER = Logger.getLogger(FilmRestController.class.getName());
	
	@Inject // @Autowired dans Spring avant la standardisation DI (JSR 330)
	private SakilaService sakilaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Film> getAllFilms() {
		return sakilaService.findAllFilms();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Film getFilm(@PathVariable("id") Integer id) {
		return sakilaService.find(id);
	}
	
	
	
}
