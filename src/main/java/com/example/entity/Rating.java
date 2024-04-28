package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "rating")
@NoArgsConstructor
public class Rating {

    @Id
    private String id;
    private int userId;
    private int movieId;
    private int rating;
    private String timestamp;

    public Rating(int userId, int movieId, int rating, String timestamp){
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}
