package com.example.service.logic;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.MoviePlace;
import com.example.entity.User;
import com.example.service.MoviePlaceService;
import com.example.store.MoviePlaceStore;
import com.example.store.UserStore;
import com.example.util.utils;

@Service
public class MoviePlaceServiceLogic implements MoviePlaceService{
    @Autowired
    MoviePlaceStore moviePlaceStore;

    @Autowired
    UserStore userStore;

    @Override //placeId를 통해 해당 MoviePlace데이터 가져오기
    public MoviePlace callMoviePlaceById(int placeId) {
        return moviePlaceStore.callbyId(placeId);
    }

    @Override //하버사인 공식을 이용하여 threshold를 넘지 않는 가까운 장소들 불러오기
    public List<MoviePlace> callMoviePlaceByDis(int userId, double threshold) {
        User user = userStore.callbyUserId(userId);
        List<MoviePlace> allMoviePlaces = moviePlaceStore.callAll();

        return allMoviePlaces.stream()
        .filter(place -> utils.haversineDistance(user.getLatitude(), user.getLongitude(), place.getLatitude(), place.getLongitude()) < threshold)
        .collect(Collectors.toList());

    } 

    @Override
    public List<MoviePlace> callAll() {
        return moviePlaceStore.callAll();
    }

    @Override //유저id를 통해 hammingdistance를 이용하여 거리가 가장 짧은 top5개를 추천하도록하여 리턴
    public List<MoviePlace> recommendMoviePlaceByUserId(int userId) {
        User user = userStore.callbyUserId(userId);
        List<MoviePlace> moviePlaces = moviePlaceStore.callAll();

        List<MoviePlace> top5places = moviePlaces.stream()
            .sorted(Comparator.comparingInt(place -> 
                utils.HammingSimilarity(user.getHobby(), place.getHobby()) +
                utils.HammingSimilarity(user.getFavoriteFood(), place.getFavoriteFood())
            ))
            .limit(5)
            .collect(Collectors.toList());
        
        return top5places;
    }
}
