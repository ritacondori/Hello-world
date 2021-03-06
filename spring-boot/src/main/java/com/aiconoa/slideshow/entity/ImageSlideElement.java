package com.aiconoa.slideshow.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="image")
public class ImageSlideElement extends SlideElementBaseEntity<String> {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ImageSlideElement [id=" + id + ", getX()=" + getX() + ", getY()=" + getY() + ", getWidth()="
                + getWidth() + ", getHeight()=" + getHeight() + ", getContent()=" + getContent() + ", toString()="
                + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }


}
