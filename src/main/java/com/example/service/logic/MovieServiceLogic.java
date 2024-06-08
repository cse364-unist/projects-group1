package com.example.service.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.example.entity.Movie;
import com.example.entity.cdo.MovieRequest;
import com.example.service.MovieService;
import com.example.store.MovieStore;

@Service
public class MovieServiceLogic implements MovieService{
    
    @Autowired
    MovieStore movieStore;

    @Override
    public Movie callMovieById(int movieId) {
        return movieStore.callById(movieId);
    }

    @Override
    public List<MovieRequest> callMoviesByPlaceId(int placeId) {
        List<Movie> movies = movieStore.callByPlaceId(placeId);
        List<MovieRequest> movieRequests = movies.stream().map(movie -> new MovieRequest(movie.getName(), movie.getGenre(), movie.getPlaceId())).collect(Collectors.toList());
        return movieRequests;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieStore.getAllMovies();
    }

}
