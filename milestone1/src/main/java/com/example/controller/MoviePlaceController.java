package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Movie;
import com.example.entity.MoviePlace;
import com.example.entity.cdo.MovieRequest;
import com.example.service.MoviePlaceService;
import com.example.service.MovieService;

@RestController
@RequestMapping("/places")
public class MoviePlaceController {

    @Autowired
    MovieService movieService;

    @Autowired
    MoviePlaceService moviePlaceService;

    //해당 영화의 촬영지에 대한 정보 가져오기
    @GetMapping("/{movieId}")
    public MoviePlace findMoviePlaceById(@PathVariable int movieId){
        Movie m = movieService.callMovieById(movieId);
        return moviePlaceService.callMoviePlaceById(m.getPlaceId());
    }

    //해당 유저id를 통해 유저의 위치를 기반으로 가까운 장소에 있는 촬영지를 List로 반환 ->threshold는 유저가 원하는 km를 반영하기 위해 RequestParam으로 설정
    @GetMapping("/recommends/distance/{threshold}")
    public List<MoviePlace> findMoviePlaceByDis(@RequestParam(required = true) int userId, @PathVariable double threshold){//threshold는 km단위 디폴트로 10km로 설정
        return moviePlaceService.callMoviePlaceByDis(userId, threshold);
    }

    //해당 촬영지 id가 오면 촬영지에 대한 정보 넘기기 -필요한 이유: 그 장소에 있는 play와 food를 보기위한 서비스 제공 비트 표현은 json데이터를 받는 프론트엔드에서 처리해야할듯?
    @GetMapping
    public MoviePlace findMoviePlaceByPlaceId(@RequestParam(required = true) int placeId){
        return moviePlaceService.callMoviePlaceById(placeId);
    }

    //현재 존재하는 모든 촬영지들 리턴
    @GetMapping("/all")
    public List<MoviePlace> findAll(){
        return moviePlaceService.callAll();
    }

    //존재하는 촬영지들 중 촬영지를 클릭하면 해당 장소에서 촬영한 무비들을 리턴
    @GetMapping("/all/{placeId}")
    public List<MovieRequest> findMoviesByPlaceId(@PathVariable int placeId){
        return movieService.callMoviesByPlaceId(placeId);
    }
    
    //userId를 받으면 이를 통해 play와 food정보를 얻고 추천된 영화장소 top5개를 리턴
    @GetMapping("/recommends/{userId}")
    public List<MoviePlace> findRecommendPlacesByUserId(@PathVariable int userId){
        return moviePlaceService.recommendMoviePlaceByUserId(userId);
    }
}
