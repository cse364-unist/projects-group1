package com.example.service;

import com.example.entity.Quiz;
import com.example.entity.cdo.QuizRequest;
import com.example.entity.cdo.UserRequest;


public interface QuizService {

    Quiz callQuizById(int quizId);

    int checkQuizAnswer(QuizRequest newQuizRequest, int quizId);
    boolean resetQuizStatus(UserRequest newQuizResetRequest, int quizId);
}
