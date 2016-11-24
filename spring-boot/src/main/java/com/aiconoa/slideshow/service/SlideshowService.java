package com.aiconoa.slideshow.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiconoa.slideshow.entity.Slideshow;
import com.aiconoa.slideshow.repository.SlideshowRepository;

@Service
public class SlideshowService {
	
	@Inject
	private SlideshowRepository slideshowRepository;

	@Transactional(readOnly=true)
	public List<Slideshow> findAllSlideshows() {
		return slideshowRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Slideshow find(Integer id) {
		return slideshowRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public Slideshow findWithSlidesAndSlideElements(int id) {
		return slideshowRepository.findWithSlidesAndSlideElements(id);
	}
	
	@Transactional(readOnly=true)
	public String findFirstImageInASlideOfTheSlideshow(Integer id) {
		return slideshowRepository.findFirstImageInASlideOfTheSlideshow(id);
	}
	
}
