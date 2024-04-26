package com.example.store.repository;

import com.example.entity.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface QuizRepository extends MongoRepository<Quiz,String>{
    Optional<Quiz> findByQuizId(int quizId);
}
