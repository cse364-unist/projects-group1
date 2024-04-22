package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.cdo.MovieRequest;
import com.example.service.MovieService;
import com.example.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    RatingService ratingService;
    @Autowired
    MovieService movieService;

    @GetMapping("/{avg}")
    public List<MovieRequest> findByAvg(@PathVariable double avg){
        List<Integer> movieIds = ratingService.findMovieIdsByAvgRating(avg);
        return movieService.callMoviesByAvg(movieIds);
    }
}
