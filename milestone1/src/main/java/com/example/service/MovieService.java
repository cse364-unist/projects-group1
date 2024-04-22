package com.example.service;

import java.util.List;

import com.example.entity.Movie;
import com.example.entity.cdo.MovieRequest;

public interface MovieService {
    int registerMovie(Movie newMovie);
    Movie callMovieById(int movieId);
    void modifyMovie(MovieRequest newMovie, int movieId);
    int findMaxMovieId();
    List<MovieRequest> callMoviesByAvg(List<Integer> movieIds);
}
