package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "place")
@NoArgsConstructor
public class MoviePlace {
    
    @Id
    private String id;
    private int placeId;
    private String name;
    private double latitude;
    private double longitude;
    private int hobby;
    private int favoriteFood;
}
