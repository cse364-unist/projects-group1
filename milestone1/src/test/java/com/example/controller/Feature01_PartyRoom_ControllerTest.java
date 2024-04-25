package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.entity.Movie;
import com.example.service.PartyRoomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

@SpringBootTest
public class Feature01_PartyRoom_ControllerTest {

    @Mock
    private PartyRoomService partyRoomService;

    @InjectMocks
    private Feature01_PartyRoom_Controller controller;
    
    // 1번 테스트 - 무작위로 선택된 세 개의 영화 목록을 반환하는 엔드포인트 -> 우선 3개가 뽑혔는지 + 장르가 모두 다른지 총 2개의 테스트
    @Test
    public void getMoviesByGenres_ReturnsThreeMoviesWithDistinctGenres() {
        // given
        Movie movie1 = new Movie(1, "Movie 1", "Drama");
        Movie movie2 = new Movie(2, "Movie 2", "Comedy");
        Movie movie3 = new Movie(3, "Movie 3", "Action");
        List<Movie> movies = Arrays.asList(movie1, movie2, movie3);
        when(partyRoomService.findRandomTop3Movies()).thenReturn(movies);

        // when
        ResponseEntity<List<Movie>> response = controller.getMoviesByGenres();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size()); // 확인: 목록의 크기가 3인가?

        // 장르가 모두 다른지 확인
        Set<String> distinctGenres = response.getBody().stream()
                                            .map(Movie::getGenre)
                                            .collect(Collectors.toSet());
        assertEquals(3, distinctGenres.size()); // 확인: 장르가 모두 다른가?
    }


    // 2번 테스트 - 
    /*@Test
    public void getMoviesByGenres_ReturnsNoContentWhenNoMovies() {
        // given
        when(partyRoomService.findRandomTop3Movies()).thenReturn(Arrays.asList());

        // when
        ResponseEntity<List<Movie>> response = controller.getMoviesByGenres();

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }*/

    // 나머지 API 테스트 케이스들도 유사한 패턴으로 작성...
}
