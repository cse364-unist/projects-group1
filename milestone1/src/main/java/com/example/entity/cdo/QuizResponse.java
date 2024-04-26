package com.example.entity.cdo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizResponse {
    private int movieId;
    private String movieName;
    private int quizNum;
    private String quizBody;
}
