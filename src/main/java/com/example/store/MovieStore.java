package com.example.store;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Movie;
import com.example.exception.MovieNotFoundException;
import com.example.store.repository.MovieRepository;

@Repository
public class MovieStore {
    @Autowired
    MovieRepository movieRepository;

    public Movie callById(int movieId){
        Optional<Movie> movie = movieRepository.findByMovieId(movieId);
        if(!movie.isPresent()){
            throw new MovieNotFoundException(movieId);
        }
        return movie.get();
    }

    public List<Movie> callByPlaceId(int placeId){
        return movieRepository.findByPlaceId(placeId);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
}
