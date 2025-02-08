package com.example.cinemax.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String title;
    private String genre;
    private String director;
    private String description;
    private int year;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

//    public long getId() {
//        return id;
//    }
}
