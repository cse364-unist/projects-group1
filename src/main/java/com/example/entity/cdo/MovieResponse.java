package com.example.entity.cdo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponse {
    private int movieId;
    private String name;
    private String genre;
}
