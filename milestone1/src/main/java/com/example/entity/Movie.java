package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "movie")
@NoArgsConstructor
public class Movie {

    @Id
    private String id;
    private int movieId;
    private String name;
    private String genre;

    public Movie(int movieId, String name, String genre){
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
    }


}
