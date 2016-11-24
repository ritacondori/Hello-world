package com.aiconoa.slideshow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aiconoa.slideshow.entity.Slideshow;
import com.aiconoa.slideshow.service.SlideshowService;

@Controller
@RequestMapping("/slideshows")
public class SlideshowController {

	private static final Logger LOGGER = Logger.getLogger(SlideshowController.class.getName());
	
	@Inject // @Autowired dans Spring avant la standardisation DI (JSR 330)
	private SlideshowService slideshowService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAllSlideshows(Model model) {
		
		List<Slideshow> slideshows = slideshowService.findAllSlideshows();
		LOGGER.info(String.format("found %d slideshows", slideshows.size()));
		model.addAttribute("slideshows", slideshows);
		
        List<String> slideshowThumbnails = new ArrayList<String>();
        for (Slideshow slideshow : slideshows) {
            // can optimize this => one request fetches all thumbnails
            slideshowThumbnails.add(slideshowService.findFirstImageInASlideOfTheSlideshow(slideshow.getId()));
        }
        model.addAttribute("slideshowThumbnails", slideshowThumbnails);
        
		return "slideshow/all-slideshows";
	}
	
	@RequestMapping(value="/{id}/slide/{index}", method=RequestMethod.GET)
	public String getSlideshow(@PathVariable("id") Integer id, @PathVariable("index") Integer index, Model model) {
		
		Slideshow slideshow = slideshowService.findWithSlidesAndSlideElements(id);
		if(slideshow == null) {
			throw new SlideshowNotFoundException();
		}
		
		index = (index != null ? index : 0); 
		
		model.addAttribute("slideshow", slideshow);
		model.addAttribute("slide", slideshow.getSlideAt(index));
		model.addAttribute("index", index);
		return "slideshow/slideshow-detail";
	}

}
