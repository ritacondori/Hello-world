package com.aiconoa.slideshow.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

// @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=As.PROPERTY, property="type")
// dans le json écrire "type": "com.aiconoa.trainings.javaee7.slideshow.model.jpa.TextSlideElement"

// @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=As.PROPERTY, property="type")
 @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.PROPERTY, property="type")
 @JsonSubTypes({ 
    @JsonSubTypes.Type(value=TextSlideElement.class, name="text"),
    @JsonSubTypes.Type(value=ImageSlideElement.class, name="image")
    })
public interface SlideElement<T> extends Serializable {

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getWidth();

    /**
     * @throws IllegalArgumentException if width < 0
     */
    void setWidth(double width);

    double getHeight();

    /**
     * @throws IllegalArgumentException if height < 0
     */
    void setHeight(double height);

    T getContent();

    /**
     * @throws NullPointerException
     *             if content is null
     * @throws IllegalArgumentException
     *             if the content type is not acceptable for a specific SlideElement
     *             implementation
     */
    void setContent(T content);
    
    @JsonBackReference("slideElements")
    public Slide getSlide() ;
    
    public void setSlide(Slide slide);

}
