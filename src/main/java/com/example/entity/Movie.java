package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "movie")
@NoArgsConstructor
public class Movie {

    @Id
    private String id;
    private int movieId;
    private String name;
    private String genre;
    private String url; // 파티룸 페이지 URL
    private int placeId; // 영화를 촬영한 장소 id
    private String imageUrl; // 이미지 URL 필드 추가

    public Movie(int movieId, String name, String genre){
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
    }
}
