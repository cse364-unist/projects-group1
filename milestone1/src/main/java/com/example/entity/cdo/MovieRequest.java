package com.example.entity.cdo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {
    private String name;
    private String genre;
    private int placeId;


    public MovieRequest(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public MovieRequest(String name, String genre, int placeId){
        this.name = name;
        this.genre = genre;
        this.placeId = placeId;
    }
}
