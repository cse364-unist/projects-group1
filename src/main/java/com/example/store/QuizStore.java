package com.example.store;

import com.example.entity.Quiz;
import com.example.exception.QuizNotFoundException;
import com.example.store.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuizStore {
    @Autowired
    QuizRepository quizRepository;

    public Quiz callByQuizId(int quizId){
        Optional<Quiz> quiz = quizRepository.findByQuizId(quizId);
        if(!quiz.isPresent()){
            throw new QuizNotFoundException(quizId);
        }
        return quiz.get();
    }
}
