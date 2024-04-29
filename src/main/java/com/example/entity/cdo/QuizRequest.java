package com.example.entity.cdo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizRequest {
    private int userId;
    private int userAnswer;

    public QuizRequest(int userId, int userAnswer){
        this.userId = userId;
        this.userAnswer = userAnswer;
    }
}
