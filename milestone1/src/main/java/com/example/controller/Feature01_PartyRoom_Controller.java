package com.example.controller;

import com.example.service.PartyRoomService;
import com.example.entity.Movie;
import com.example.entity.Content;
//import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/partyroom")
public class Feature01_PartyRoom_Controller {

    private final PartyRoomService partyRoomService;

    // 각 장르에 해당하는 영화를 찾아서 반환하는 서비스 클래스 => 장르 중 3개를 골라서 각각 리턴할거야.
    @Autowired
    public Feature01_PartyRoom_Controller(PartyRoomService partyRoomService) {
        this.partyRoomService = partyRoomService;
    }

    // 무작위로 선택된 세 개의 영화 목록을 반환하는 엔드포인트
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMoviesByGenres() {
        List<Movie> movies = partyRoomService.findRandomTop3Movies();
        if (movies == null || movies.isEmpty()) {
            // 영화 목록이 비어있거나 null인 경우, 클라이언트에 NO_CONTENT 상태 코드를 반환
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
        }
        movies.forEach(movie -> movie.setUrl("http://localhost:8080/movies/" + movie.getMovieId()));
        // 영화 목록이 있는 경우, OK 상태 코드와 함께 영화 목록을 반환
        return ResponseEntity.ok(movies);
    }


    /* Example:
     * [{"id":"662695797e2417433ba94ff0","movieId":2432,"name":"Stepmom (1998)","genre":"Drama","url":"http://localhost:8080/movies/2432"},{"id":"662695797e2417433ba94933","movieId":644,"name":"Happy Weekend (1996)","genre":"Comedy","url":"http://localhost:8080/movies/644"},{"id":"662695797e2417433ba94990","movieId":742,"name":"Thinner (1996)","genre":"Horror|Thriller","url":"http://localhost:8080/movies/742"}]
     */

    // Content Zone -> generate을 해야함
    @PostMapping("/contents")
    public Content createContent(@RequestBody Map<String, String> body) {
        String contentName = body.get("contentName");
        // 여기에서 URL을 구성하고, Content 객체를 생성한 다음 저장
        Content content = new Content(contentName, "http://localhost:8080/contents/" + contentName);
        return partyRoomService.createContent(content);
    }

    // 만들어진 컨텐츠 모두 보여주기
    @GetMapping("/contents")
    public ResponseEntity<?> getAllContents() {
        List<Content> contents = partyRoomService.findAllContents();
        
        if (contents.isEmpty()) {
            // 내용이 비어 있을 때 적절한 상태 코드와 메시지 반환
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No contents available");
        }
        
        return ResponseEntity.ok(contents);
    }
    
    // POST로 만든걸 제거하는 것.
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
    // 웹페이지 기능들 (실시간 영상(=이미지), live chat)
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<?> getPartyRoomDetails(@PathVariable int movieId) {
        // Fetch the movie details
        Movie movie = partyRoomService.getMovieById(movieId);

        // Construct response
        HashMap<String, String> response = new HashMap<>();
        response.put("movieName", movie.getName()); // => front end
        response.put("streamVideoUrl", "http://videostreaming.com/partyroom/" + String.valueOf(movieId)); // => frontend 구현 부분
        response.put("chatUrl", "ws://localhost:8080/partyroom/chat/" + String.valueOf(movieId)); // => frontend 구현 부분

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contents/{contentName}")
    public ResponseEntity<?> getPartyRoomDetailsWithContent(@PathVariable String contentName) {
        // {contentsName}으로 데이터 패치
        Content content = partyRoomService.getContentByContentName(contentName);

        // 똑같은 구조의 response
        HashMap<String, String> response = new HashMap<>();
        response.put("contentName", content.getName()); // => front end
        response.put("streamVideoUrl", "http://videostreaming.com/partyroom/" + contentName); // => frontend 구현 부분
        response.put("chatUrl", "ws://localhost:8080/partyroom/chat/" + contentName); // => frontend 구현 부분

        return ResponseEntity.ok(response);
    }

}


