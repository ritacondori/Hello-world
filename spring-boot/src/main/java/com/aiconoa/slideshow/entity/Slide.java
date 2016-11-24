package com.aiconoa.slideshow.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="slide")
public class Slide implements Serializable, Comparable<Slide> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer position;
    
    @Column(name="author_comment")
    private String authorComment;
    
    @JsonBackReference("slideshow")
    @ManyToOne
    @JoinColumn(
            name = "slideshow_id",
            foreignKey = @ForeignKey(name = "fk_slide_slideshow")
            )
    private Slideshow slideshow;

    @JsonManagedReference("slideElements")
    @OneToMany(
            mappedBy="slide", 
            targetEntity=SlideElementBaseEntity.class,
            cascade=CascadeType.ALL)
    @OrderBy("id ASC") // TODO intégrer la notion de position d'un slide element
//    private List<SlideElementBaseEntity<?>> slideElements;
    private Set<SlideElement<?>> slideElements;
    
    public List<SlideElement<?>> getSlideElements() {
//    public List<SlideElementBaseEntity<?>> getSlideElements() {
        return new ArrayList<SlideElement<?>>(slideElements);
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getPosition() {
        return position;
    }
    
    public String getAuthorComment() {
        return authorComment;
    }
    
    @Override
    public int compareTo(Slide o) {
        return position.compareTo(o.position);
    }

    @Override
    public String toString() {
        return "Slide [id=" + id + ", position=" + position + ", authorComment=" + authorComment + ", slideElements=" + slideElements + "]";
    }
}
