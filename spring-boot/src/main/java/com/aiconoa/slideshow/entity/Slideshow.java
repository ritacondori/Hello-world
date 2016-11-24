package com.aiconoa.slideshow.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="slideshow")
@NamedEntityGraph(name = "graph.Slideshow.slides.slideelements", 
                  attributeNodes = @NamedAttributeNode(value = "slides", subgraph = "slideElements"), 
                  subgraphs = @NamedSubgraph(name = "slideElements", attributeNodes = @NamedAttributeNode("slideElements")))
public class Slideshow implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    private Integer width;
    private Integer height;
    
    @JsonManagedReference("slideshow")
    @OneToMany(mappedBy="slideshow", cascade=CascadeType.ALL)
    @SortNatural 
    // @OrderBy doute subsiste sur @OrderBy et Set dans Hibernate.
    // A vérifier dans la doc de Hibernate ou de JPA ? => si rien de claire alors utiliser tests unitaires
    private SortedSet<Slide> slides;

    public Slideshow() {
        this.slides = new TreeSet<>();
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @throws NullPointerException if slide is null
     */
    public void addSlide(Slide slide) {
        Objects.requireNonNull(slide, "slide must not be null");
        slides.add(slide);
    }

    /**
     * @throws NullPointerException if slide is null
     */
    public void removeSlide(Slide slide) {
        Objects.requireNonNull(slide, "slide must not be null");
        slides.remove(slide);
    }
    
    public int size() {
        return slides.size();
    }
    
    public Slide[] getSlides() {
       return slides.toArray(new Slide[0]);
    }

    /**
     * @throws IndexOutOfBoundsException if index is < 0 or >= number of slides
     */
    private void rangeCheck(int index) {
        if(index < 0 || index >= slides.size()) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d", index, slides.size()));
        }
    }

    /**
     * @throws IndexOutOfBoundsException if currentSlideIndex is < 0 or >= number of slides
     */
    public Slide getSlideAt(int currentSlideIndex) {
        rangeCheck(currentSlideIndex);

        return this.getSlides()[currentSlideIndex];
    }

    @Override
    public String toString() {
        return "Slideshow [id=" + id + ", title=" + title + ", width=" + width + ", height=" + height + ", slides="
                + slides + "]";
    }


}
