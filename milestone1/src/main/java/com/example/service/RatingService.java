package com.example.service;

import java.util.List;

public interface RatingService {
    List<Integer> findMovieIdsByAvgRating(double criteriaAvg);
}
