package com.aiconoa.slideshow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aiconoa.slideshow.entity.Slideshow;

public interface SlideshowRepository extends JpaRepository<Slideshow, Integer> {

	@EntityGraph(type=EntityGraphType.LOAD, value="graph.Slideshow.slides.slideelements")
	@Query("SELECT DISTINCT s FROM Slideshow s WHERE s.id = :id")
	public Slideshow findWithSlidesAndSlideElements(@Param("id") int id);
	
	@EntityGraph(type=EntityGraphType.LOAD, value="graph.Slideshow.slides.slideelements")
	@Query("SELECT DISTINCT s FROM Slideshow s")
    public List<Slideshow> findAllSlideshowWithSlidesAndSlideElements();
    
	@Query(value = "SELECT slideelement.content FROM slideshow INNER JOIN slide ON slideshow.id = slide.slideshow_id AND slideshow.id = :id INNER JOIN slideelement ON slide.id = slideelement.slide_id AND slideelement.type = 'image' ORDER BY slide.position LIMIT 1",
		   nativeQuery=true)
    public String findFirstImageInASlideOfTheSlideshow(@Param("id") int id);
}
