package com.example.service;

import java.util.List;

import com.example.entity.MoviePlace;

public interface MoviePlaceService {
    MoviePlace callMoviePlaceById(int placeId);
    List<MoviePlace> callMoviePlaceByDis(int userId, double threshold);
    List<MoviePlace> callAll();
    List<MoviePlace> recommendMoviePlaceByUserId(int userId);
}
