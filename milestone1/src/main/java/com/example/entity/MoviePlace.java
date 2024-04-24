package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "place")
@NoArgsConstructor
public class MoviePlace {
    
    @Id
    private String id;
    private int placeId;
    private String name;
    private double latitude;
    private double longitude;
    private int plays;
    private int foods;
}