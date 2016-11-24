package com.aiconoa.sakila.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="film_id")
    private int id;

    private String title;
    
    
//    @Column(name="language_id")
//    private int languageId;

    @ManyToOne
    @JoinColumn(
            name = "language_id",
            foreignKey = @ForeignKey(name = "fk_film_language")
            )
    private Language languageId;
    
    
    @ManyToMany
    @JoinTable(name = "film_actor",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors; // attention ordre ? => transformé en Bag par hibernate
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguageId() {
        return languageId;
    }
    
    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public List<Actor> getActors() {
        return actors;
    }
    
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", title=" + title + ", languageId=" + languageId + "]";
    }

}
