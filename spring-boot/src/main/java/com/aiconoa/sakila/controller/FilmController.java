package com.aiconoa.sakila.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.aiconoa.sakila.entity.Film;
import com.aiconoa.sakila.service.SakilaService;

@Controller
@RequestMapping("/films")
public class FilmController {

	private static final Logger LOGGER = Logger.getLogger(FilmController.class.getName());
	
	@Inject // @Autowired dans Spring avant la standardisation DI (JSR 330)
	private SakilaService sakilaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAllFilms(Model model) {
		
		List<Film> films = sakilaService.findAllFilms();
		LOGGER.info(String.format("found %d films", films.size()));
		model.addAttribute("films", films);

		return "sakila/all-films";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getFilm(@PathVariable("id") Integer id, Model model) {
		
		Film film = sakilaService.find(id);
		if(film == null) {
			// throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			throw new FilmNotFoundException();
		}
		model.addAttribute("film", film);

		return "sakila/film-detail";
	}
	
	
	
	
}
