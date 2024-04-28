package com.example.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.MoviePlace;
import com.example.store.repository.MoviePlaceRepository;

@Repository
public class MoviePlaceStore {
    @Autowired
    MoviePlaceRepository moviePlaceRepository;

    public MoviePlace callbyId(int PlaceId){
        return moviePlaceRepository.findByPlaceId(PlaceId).get();
    }

    public List<MoviePlace> callAll(){
        return moviePlaceRepository.findAll();
    }
}
