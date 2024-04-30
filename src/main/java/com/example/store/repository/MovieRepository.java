package com.example.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.entity.Movie;

public interface MovieRepository extends MongoRepository<Movie,String>{
    Optional<Movie> findByMovieId(int movieId);
    List<Movie> findByPlaceId(int placeId);
}
