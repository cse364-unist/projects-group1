package com.example.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.store.repository.RatingRepository;

@Repository
public class RatingStore {
    @Autowired
    RatingRepository ratingRepository;
}
