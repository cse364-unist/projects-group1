package com.example.service;

import java.util.List;

import com.example.entity.Movie;
import com.example.entity.cdo.MovieRequest;

public interface MovieService {
    Movie callMovieById(int movieId);
    List<MovieRequest> callMoviesByPlaceId(int placeId);
}
