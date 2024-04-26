package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private int userId;
    private String gender;
    private int age;
    private int occupation;
    private String zipCode;
    private int point;
    private int movieStatus;
    private boolean partyRoomStatus;
    private int hobby; //5개 비트 순서당 -관람, 레저, 카페, 자연, ?  
    private int favoriteFood; //5개 비트 순서당 - 한식, 중식, 일식, 디저트, 베트남
    private double latitude;
    private double longitude;

    public User(int userId, String gender, int age, int occupation, String zipCode, int point, int movieStatus, int partyRoomStatus, int hobby, int favoriteFood, double latitude, double longitude){
        this.userId = userId;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
        this.point = point;
        this.movieStatus = movieStatus;
        this.partyRoomStatus = false;
        this.hobby = hobby;
        this.favoriteFood = favoriteFood;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
