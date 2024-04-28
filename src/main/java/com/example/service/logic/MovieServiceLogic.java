package com.example.service.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.example.entity.Movie;
import com.example.entity.cdo.MovieRequest;
import com.example.service.MovieService;
import com.example.store.MovieStore;

@Service
public class MovieServiceLogic implements MovieService{
    
    @Autowired
    MovieStore movieStore;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int registerMovie(Movie newMovie) {
        newMovie.setMovieId(findMaxMovieId()+1);
        return movieStore.create(newMovie);
    }

    @Override
    public Movie callMovieById(int movieId) {
        return movieStore.callById(movieId);
    }
    
    @Override
    public int findMaxMovieId() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.sort(Sort.Direction.DESC, "movieId"),
            Aggregation.limit(1)
        );

        AggregationResults<Movie> result = mongoTemplate.aggregate(aggregation, "movie", Movie.class);
        Movie maxMovieIdMovie = result.getUniqueMappedResult();
        return maxMovieIdMovie.getMovieId();
    }

    @Override
    public void modifyMovie(MovieRequest newMovie, int movieId) {
        Movie movie = movieStore.callById(movieId);
        movie.setName(newMovie.getName());
        movie.setGenre(newMovie.getGenre());
        movieStore.update(movie);
    }

    @Override
    public List<MovieRequest> callMoviesByAvg(List<Integer> movieIds) {
        List<Movie> movies = movieStore.callByAvg(movieIds);

        List<MovieRequest> movieRequests = movies.stream().map(movie -> new MovieRequest(movie.getName(), movie.getGenre())).collect(Collectors.toList());
        
        return movieRequests;
    }

    @Override
    public List<MovieRequest> callMoviesByPlaceId(int placeId) {
        List<Movie> movies = movieStore.callByPlaceId(placeId);
        List<MovieRequest> movieRequests = movies.stream().map(movie -> new MovieRequest(movie.getName(), movie.getGenre(), movie.getPlaceId())).collect(Collectors.toList());
        return movieRequests;
    }
}
