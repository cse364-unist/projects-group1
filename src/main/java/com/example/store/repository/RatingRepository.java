package com.example.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
    
}
