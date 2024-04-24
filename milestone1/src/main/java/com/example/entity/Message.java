package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document
public class Message {
    @Id
    private String id;
    private String from;
    private String text;
    private long timestamp;

    // Getters and Setters
}
