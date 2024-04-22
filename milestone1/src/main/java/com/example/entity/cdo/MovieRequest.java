package com.example.entity.cdo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {
    private String name;
    private String genre;

    public MovieRequest(String name, String genre){
        this.name = name;
        this.genre = genre;
    }
}
