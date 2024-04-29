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

    public int create(Movie newMovie){
        movieRepository.save(newMovie);
        return newMovie.getMovieId();
    }

    public Movie callById(int movieId){
        Optional<Movie> movie = movieRepository.findByMovieId(movieId);
        if(!movie.isPresent()){
            throw new MovieNotFoundException(movieId);
        }
        return movie.get();
    }

    public void update(Movie newMovie){
        movieRepository.save(newMovie);
    }

    public List<Movie> callByAvg(List<Integer> movieIds){
        return movieRepository.findByMovieIdIn(movieIds);
    }

    public List<Movie> callByPlaceId(int placeId){
        return movieRepository.findByPlaceId(placeId);
    }
}
