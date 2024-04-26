package com.example.entity.cdo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizCheckResponse {
    private int quizId;
    private String resultMessage;
}
