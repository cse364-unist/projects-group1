package com.example.service;

import com.example.entity.Quiz;
import com.example.entity.cdo.QuizRequest;


public interface QuizService {

    Quiz callQuizById(int quizId);

    int checkQuizAnswer(QuizRequest newQuizRequest, int quizId);
}
