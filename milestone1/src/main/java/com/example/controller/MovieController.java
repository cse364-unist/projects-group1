package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Movie;
import com.example.entity.cdo.MovieRequest;
import com.example.entity.cdo.MovieResponse;
import com.example.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    public int register(@RequestBody MovieRequest newMovie){
        Movie movie = new Movie();
        movie.setName(newMovie.getName());
        movie.setGenre(newMovie.getGenre());
        return movieService.registerMovie(movie);
    }

    @GetMapping("/{movieId}")
    public MovieResponse findById(@PathVariable int movieId){
        Movie m = movieService.callMovieById(movieId);
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setMovieId(m.getMovieId());
        movieResponse.setName(m.getName());
        movieResponse.setGenre(m.getGenre());
        return movieResponse;
    }

    @PutMapping("/{movieId}")
    public void modify(@RequestBody MovieRequest movieRequest, @PathVariable int movieId){
        movieService.modifyMovie(movieRequest, movieId);
    }
}
