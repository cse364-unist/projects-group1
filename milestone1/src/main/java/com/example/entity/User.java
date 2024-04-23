package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    private int partyRoomStatus;
    private int hobby;
    private int favoriteFood;

    public User(int userId, String gender, int age, int occupation, String zipCode, int point, int movieStatus, int partyRoomStatus, int hobby, int favoriteFood){
        this.userId = userId;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
        this.point = point;
        this.movieStatus = movieStatus;
        this.partyRoomStatus = partyRoomStatus;
        this.hobby = hobby;
        this.favoriteFood = favoriteFood;
    }

}
