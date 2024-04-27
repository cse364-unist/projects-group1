package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.entity.MoviePlace;
import com.example.entity.cdo.MovieRequest;
import com.example.service.MoviePlaceService;
import com.example.service.MovieService;
import com.example.util.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MoviePlaceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MoviePlaceService moviePlaceService;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MoviePlaceController controller;

    @Test //movieId를 통해 place정보가 잘 들어오는지
    public void findMoviePlaceById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("placeId").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("loc16"))
                .andExpect(MockMvcResultMatchers.jsonPath("hobby").value(18))
                .andExpect(MockMvcResultMatchers.jsonPath("favoriteFood").value(27));
    }

    @Test //없는 영화 ID를통해 get을시도할 경우isnotfound하는실패 테스트
    public void findMoviePlaceByIDFail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places/-1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test //placeid를 통해 해당 place정보 가져오기
    public void findMoviePlaceByPlaceId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places?placeId=1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("placeId").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("name").value("loc1"))
        .andExpect(MockMvcResultMatchers.jsonPath("hobby").value(28))
        .andExpect(MockMvcResultMatchers.jsonPath("favoriteFood").value(23));
    }
    
    @Test //없는 placeid 접근시 isnotfound
    public void findMoviePlaceByPlaceIdFail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places/?placeId=400"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test //all해서 있는 place전체 데이터 불러와서 개수 300개가 맞는지 test
    public void findAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(300));
    }

    @Test //all했지만 아무 데이터가 없을 경우 제대로 메세지를 전달하는 지 테스트
    public void findAll_noplaces(){
        when(moviePlaceService.callAll()).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = controller.findAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No MoviePlaces", response.getBody());
    }

    @Test
    public void findMoviesByPlaceId(){
        List<MovieRequest> expectedmovies = Arrays.asList(
            new MovieRequest("movie1", "Action", 10),
            new MovieRequest("movie2", "Romance", 10),
            new MovieRequest("movie3", "Comedy", 10)
        );
        when(movieService.callMoviesByPlaceId(10)).thenReturn(expectedmovies);

        ResponseEntity<?> response = controller.findMoviesByPlaceId(10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<?> responsebody = (List<?>) response.getBody();
        assertEquals(expectedmovies.size(), responsebody.size()); //size가 같은지 확인
        for(Object object : responsebody){
            assertTrue(object instanceof MovieRequest);
            MovieRequest movie = (MovieRequest) object;
            assertEquals(movie.getPlaceId(), 10); //불러온 데이터들의 palceId가 10인지 확인
        }
    }

    @Test //해당placeId를 가진 영화가 없을 때
    public void findMoviePlaceById_noPlaceId(){
        when(movieService.callMoviesByPlaceId(1)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = controller.findMoviesByPlaceId(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No Movies has that placeId", response.getBody());
    }

    @Test //threshold를 0보다 작거나 같은 경우 테스트
    public void findMoviePlaceByDis_invalidThreshold(){
        when(moviePlaceService.callMoviePlaceByDis(0, -1)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = controller.findMoviePlaceByDis(0, -1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid threshold", response.getBody());
    }

    @Test //해당 threshold를 만족하는 movieplace가 없는 경우
    public void findMoviePlaceByDis_noMoviePlace(){
        when(moviePlaceService.callMoviePlaceByDis(0, 20)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = controller.findMoviePlaceByDis(0, 20);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No moviePlaces at a distance less than that threshold", response.getBody());
    }

    @Test //userId와 threshold를 통해 얻어온 json데이터들이 실제로 threshold보다 작은 하버사인거리를 가지는 지 테스트
    public void findMoviePlaceByDis() throws Exception {
        double user1_latitude = 38.83448321079011;
        double user1_longitude = 126.7516386588666;
        mockMvc.perform(MockMvcRequestBuilders.get("/places/recommends/distance/70?userId=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {//얻어진 json을 스트링으로 바꾸고 이를 다시java의 객체 형태로 변환후 for문사용
                    String jsonResponse = result.getResponse().getContentAsString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<MoviePlace> moviePlaces = objectMapper.readValue(jsonResponse, new TypeReference<List<MoviePlace>>() {});
                    for(MoviePlace moviePlace : moviePlaces){
                        double distance = utils.haversineDistance(user1_latitude, user1_longitude, moviePlace.getLatitude(), moviePlace.getLongitude());
                        assertTrue(distance < 70);
                    }
                });
    }

    @Test // 추천한 촬영지가 5개가 맞는 지
    public void findRecommendPlacesByUserId_five() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places/recommends/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5));
    }

    @Test //실제로 hammingsimilarity가 잘 반영되는 지 테스트
    public void findRecommendPlacesByUserId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/places/recommends/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(result -> {
                ObjectMapper objectMapper = new ObjectMapper();
                List<MoviePlace> moviePlaces = objectMapper.readValue(result.getResponse().getContentAsString(),
                                                                      new TypeReference<List<MoviePlace>>() {});
                boolean hasPerfectMatch = moviePlaces.stream() //userId1인 usre의 hobby는 2이고 favoritefood는 10이다. 촬영지 중에 이와 동일한 hobby와 favoritefood가 있기에 유사도가 0인 촬영지가 있어야함
                    .anyMatch(place -> utils.HammingSimilarity(place.getHobby(), 2) == 0 &&
                                        utils.HammingSimilarity(place.getFavoriteFood(), 10) == 0);
                assertTrue(hasPerfectMatch);
            });
    }







}
