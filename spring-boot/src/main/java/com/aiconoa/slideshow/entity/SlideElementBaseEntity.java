package com.aiconoa.slideshow.entity;

import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="slideelement")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public abstract class SlideElementBaseEntity<T> implements SlideElement<T> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Integer id;
    
    private double x;
    private double y;
    private double width;
    private double height;
    
    private T content;
    
    @ManyToOne
    @JoinColumn(
            name = "slide_id",
            foreignKey = @ForeignKey(name = "fk_slideelement_slide")
            )
    private Slide slide;

    public Slide getSlide() {
        return slide;
    }
    
    public void setSlide(Slide slide) {
        this.slide = slide;
    }
    
    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    /**
     * @throws IllegalArgumentException if width < 0
     */
    @Override
    public void setWidth(double width) {
        if(width < 0) {
            // throw new RuntimeException("width must be > 0");
            throw new IllegalArgumentException("width must be > 0");
        }
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    /**
     * @throws IllegalArgumentException if height < 0
     */
    @Override
    public void setHeight(double height) {
        if(height < 0) {
            throw new IllegalArgumentException("height must be > 0");
        }
        
        this.height = height;
    }

    @Override
    public T getContent() {
        return content;
    }

    /**
     * @throws NullPointerException if content is null
     */
    public void setContent(T content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

}
