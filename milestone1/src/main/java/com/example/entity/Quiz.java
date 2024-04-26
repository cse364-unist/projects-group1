package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Document(collection = "quiz")
@NoArgsConstructor
public class Quiz {
    @Id
    private String id;
    private int quizId;
    private int movieId;
    private int quizNum;
    private String quizBody;
    private int quizAnswer;

    public Quiz(int quizId, int movieId, int quizNum, String quizBody, int quizAnswer){
        this.quizId = quizId;
        this.movieId = movieId;
        this.quizNum = quizNum;
        this.quizBody = quizBody;
        this.quizAnswer = quizAnswer;
    }
}
