package com.example.store.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.MoviePlace;

import java.util.Optional;


public interface MoviePlaceRepository extends MongoRepository<MoviePlace, String>{
    Optional<MoviePlace> findByPlaceId(int placeId);
}
