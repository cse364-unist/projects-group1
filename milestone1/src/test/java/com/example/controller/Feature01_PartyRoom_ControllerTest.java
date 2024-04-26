package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.entity.Movie;
import com.example.entity.Content;
import com.example.service.PartyRoomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;
import java.util.*;

@SpringBootTest
public class Feature01_PartyRoom_ControllerTest {

    @Mock
    private PartyRoomService partyRoomService;

    @InjectMocks
    private Feature01_PartyRoom_Controller controller;
    
    // 1번 테스트 - 무작위로 선택된 세 개의 영화 목록을 반환하는 엔드포인트 -> 우선 3개가 뽑혔는지 + 장르가 모두 다른지 총 2개의 테스트
    // (통과) 1-1번
    @Test
    public void getMoviesByGenres_ReturnsThreeMovies() {
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
    }

    
    // (통과) 1-2번
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

        // 장르가 모두 다른지(=unique) 확인
        Set<String> distinctGenres = response.getBody().stream()
                                            .map(Movie::getGenre)
                                            .collect(Collectors.toSet());
        assertEquals(3, distinctGenres.size()); // 확인: 장르가 모두 다른가?
    }


    // 2번 테스트 - 올바른 입력이 주어졌을 때, 제대로된 URL을 반환하는지 확인 + 잘못된 입력이 주어졌을 때, bad request을 반환하는지 확인
    //(통과) 2-1번 -> response랑 맞는지 확인
    @Test
    public void createContent_ReturnsCreatedContent() {
        // given
        String contentName = "MyNewMovie";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("contentName", contentName);
        Content createdContent = new Content(contentName, "http://localhost:8080/contents/" + contentName);
        when(partyRoomService.createContent(any(Content.class))).thenReturn(createdContent);

        // when
        ResponseEntity<?> response = controller.createContent(requestBody);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdContent, response.getBody());
    }

    //(통과) 2-2번 -> 없는 요청 케이스 해결 잘하나확인
    @Test
    public void createContent_ReturnsBadRequestForNullContentName() {
        // given
        Map<String, String> requestBody = new HashMap<>();
        // contentName이 없는 요청

        // when
        ResponseEntity<?> response = controller.createContent(requestBody);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Content name cannot be null", response.getBody());
    }


    //  3번
    // 3-1번 -> 데이터 컨텐츠 반환을 잘 하는지
    @Test
    public void getAllContents_ReturnsContentList() {
        // given
        List<Content> expectedContents = Arrays.asList(
            new Content("Content1", "http://localhost:8080/contents/Content1"),
            new Content("Content2", "http://localhost:8080/contents/Content2")
        );
        when(partyRoomService.findAllContents()).thenReturn(expectedContents);

        // when
        ResponseEntity<?> response = controller.getAllContents();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Should return OK status");
        assertNotNull(response.getBody(), "Body should not be null");
        List<?> responseBody = (List<?>) response.getBody();
        assertEquals(expectedContents.size(), responseBody.size(), "Should return the correct number of contents");
        for (int i = 0; i < expectedContents.size(); i++) {
            assertEquals(expectedContents.get(i), responseBody.get(i), "Each content should match the expected content");
        }
    }


    // (통과) 3-2번 -> 없는 경우 처리 
    @Test
    public void getAllContents_ReturnsNoContent() {
        // given
        when(partyRoomService.findAllContents()).thenReturn(new ArrayList<>());

        // when
        ResponseEntity<?> response = controller.getAllContents();

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No contents available", response.getBody());
    }

    // 4번
    // (통과) 4-1번  -> 잘 없어졌냐?
    @Test
    public void removeContent_DeletesExistingContent() {
        // given
        String contentName = "Movie 1";
        when(partyRoomService.deleteContentByName(contentName)).thenReturn(true);

        // when
        ResponseEntity<?> response = controller.removeContent(contentName);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Content successfully deleted.", response.getBody());
    }

    // (통과) 4-2번 -> 없는거 없애진 않았지?
    @Test
    public void removeContent_ReturnsNotFoundForNonExistingContent() {
        // given
        String nonExistingContentName = "NonExistingMovie";
        when(partyRoomService.deleteContentByName(nonExistingContentName)).thenReturn(false);

        // when
        ResponseEntity<?> response = controller.removeContent(nonExistingContentName);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // 5번, 6번 똑같음 형식 -> movie는 다 DB에 init 되어 있어서 처리 가능해
    //(통과) 5-1번 -> url 잘 맞게 있나?
    @Test
    public void getPartyRoomDetails_ReturnsMovieDetails() {
        // given
        Movie movie = new Movie(4, "Waiting to Exhale (1995)", "Comedy|Drama");
        when(partyRoomService.getMovieById(4)).thenReturn(movie);

        // when
        ResponseEntity<?> response = controller.getPartyRoomDetails(4);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals("Waiting to Exhale (1995)", responseBody.get("movieName"));
        assertEquals("http://videostreaming.com/partyroom/4", responseBody.get("streamVideoUrl"));
        assertEquals("ws://localhost:8080/partyroom/chat/4", responseBody.get("chatUrl"));
    }

    // 5-2번 -> 잘못된 request 처리
    //(통과) 5-2번
    @Test
    public void getPartyRoomDetails_ReturnsNotFound() {
        // given
        int nonExistingMoiveID = -5;
        when(partyRoomService.getMovieById(nonExistingMoiveID)).thenReturn(null);

        // when
        ResponseEntity<?> response = controller.getPartyRoomDetails(nonExistingMoiveID);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    

    // 6번
    //(통과) 6-1번
    @Test
    public void getPartyRoomDetails_ReturnsContentDetails() {
        // given
        String contentName = "NewNew";
        Content content = new Content();
        content.setName(contentName);
        when(partyRoomService.getContentByContentName(contentName)).thenReturn(content);

        // when
        ResponseEntity<?> response = controller.getPartyRoomDetailsWithContent(contentName);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals("NewNew", responseBody.get("contentName"));
        assertEquals("http://videostreaming.com/partyroom/NewNew", responseBody.get("streamVideoUrl"));
        assertEquals("ws://localhost:8080/partyroom/chat/NewNew", responseBody.get("chatUrl"));
    }

    //(통과) 6-2번
    @Test
    public void getPartyRoomDetailsWithNonExistingContent_ReturnsNotFound() {
        // given
        String nonExistingContentName = "NonExistingContent";
        when(partyRoomService.getContentByContentName(nonExistingContentName)).thenReturn(null);

        // when
        ResponseEntity<?> response = controller.getPartyRoomDetailsWithContent(nonExistingContentName);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // 나머지 API 테스트 케이스들도 유사한 패턴으로 작성...
}
