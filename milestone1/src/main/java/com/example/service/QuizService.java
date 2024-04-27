package com.example.service;

import com.example.entity.Quiz;
import com.example.entity.cdo.QuizRequest;
import org.springframework.stereotype.Service;

@Service
public interface QuizService {

    Quiz callQuizById(int quizId);

    int checkQuizAnswer(QuizRequest newQuizRequest, int quizId);
}
