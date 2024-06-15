package com.example.controller;

import com.example.service.PartyRoomService;
import com.example.entity.Movie;
import com.example.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/partyroom")
public class Feature01_PartyRoom_Controller {

    private final PartyRoomService partyRoomService;

    @Autowired
    public Feature01_PartyRoom_Controller(PartyRoomService partyRoomService) {
        this.partyRoomService = partyRoomService;
    }

    // 무작위로 선택된 세 개의 영화 목록을 반환하는 엔드포인트
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMoviesByGenres() {
        List<Movie> movies = partyRoomService.findRandomTop3Movies();
        if (movies == null || movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
        }
        movies.forEach(movie -> movie.setUrl("http://localhost:8080/movies/" + movie.getMovieId()));
        return ResponseEntity.ok(movies);
    }

    // Content Zone -> generate을 해야함
    @PostMapping("/contents")
    public ResponseEntity<?> createContent(@RequestParam String contentName) {
        if (contentName == null || contentName.isEmpty()) {
            return ResponseEntity.badRequest().body("Content name cannot be null");
        }

        Content content = new Content(contentName, "http://localhost:8080/contents/" + contentName);
        Content createdContent = partyRoomService.createContent(content);
        return ResponseEntity.ok(createdContent);
    }

    // 만들어진 컨텐츠 모두 보여주기
    @GetMapping("/contents")
    public ResponseEntity<?> getAllContents() {
        List<Content> contents = partyRoomService.findAllContents();

        if (contents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No contents available");
        }

        return ResponseEntity.ok(contents);
    }

    // DELETE로 만든걸 제거하는 것.
    @DeleteMapping("/contents/{contentName}")
    public ResponseEntity<?> removeContent(@PathVariable String contentName) {
        boolean isDeleted = partyRoomService.deleteContentByName(contentName);
        if (isDeleted) {
            return ResponseEntity.ok("Content successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 특정 영화에 대한 상세 정보를 반환하는 엔드포인트. 이 정보에는 영화 이름, 이미지 URL, 채팅 URL이 포함
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<?> getPartyRoomDetails(@PathVariable int movieId) {
        Movie movie = partyRoomService.getMovieById(movieId);
        if (movie == null) {
            return ResponseEntity.badRequest().build();
        }

        HashMap<String, String> response = new HashMap<>();
        response.put("movieName", movie.getName());
        response.put("streamVideoUrl", "http://videostreaming.com/partyroom/" + movieId);
        response.put("chatUrl", "ws://localhost:8080/partyroom/chat/" + movieId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contents/{contentName}")
    public ResponseEntity<?> getPartyRoomDetailsWithContent(@PathVariable String contentName) {
        Content content = partyRoomService.getContentByContentName(contentName);
        if (content == null) {
            return ResponseEntity.badRequest().build();
        }

        HashMap<String, String> response = new HashMap<>();
        response.put("contentName", content.getName());
        response.put("streamVideoUrl", "http://videostreaming.com/partyroom/" + contentName);
        response.put("chatUrl", "ws://localhost:8080/partyroom/chat/" + contentName);

        return ResponseEntity.ok(response);
    }
}