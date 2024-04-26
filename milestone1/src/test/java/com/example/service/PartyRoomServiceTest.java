package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.example.entity.Movie;
import com.example.entity.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.client.result.DeleteResult;

import java.util.*;

@SpringBootTest
public class PartyRoomServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private PartyRoomService partyRoomService;
    
    /*@Test
    void findRandomTop3Movies_ShouldReturnListOfMoviesWithDistinctGenres() {
        // Given
        // When
        List<Movie> randomMovies = partyRoomService.findRandomTop3Movies();

        // Then
        // 각 영화의 장르가 서로 다른지 확인하는 로직이 서비스 메소드에 포함되어 있다고 가정하자
        assertNotNull(randomMovies, "The returned movie list should not be null");
        assertEquals(3, randomMovies.size(), "The list should contain exactly 3 movies");

        // 장르가 모두 다른지 확인
        long distinctGenreCount = randomMovies.stream()
                .map(Movie::getGenre)
                .distinct()
                .count();
        assertEquals(3, distinctGenreCount, "All movies should have distinct genres");
    }*/


    @Test
    void getMovieById_ShouldReturnMovie() {
        // Given
        Movie expectedMovie = new Movie(1, "Toy Story (1995)", "Animation|Children's|Comedy");
        when(mongoTemplate.findOne(any(Query.class), any())).thenReturn(expectedMovie);

        // When
        Movie actualMovie = partyRoomService.getMovieById(1);

        // Then
        assertNotNull(actualMovie, "Movie should not be null");
        assertEquals(expectedMovie, actualMovie, "Expected and actual movie should match");
    }

}
