package com.example.controller;

import com.example.entity.Quiz;
import com.example.entity.Movie;
import com.example.entity.cdo.QuizRequest;
import com.example.entity.cdo.QuizCheckResponse;
import com.example.service.MovieService;
import com.example.service.UserService;
import com.example.service.QuizService;
import com.example.entity.cdo.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    QuizService quizService;
    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;

    @GetMapping("/{quizId}")
    public QuizResponse findById(@PathVariable int quizId){
        Quiz q = quizService.callQuizById(quizId);
        int movieId = q.getMovieId();
        Movie m = movieService.callMovieById(movieId);

        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setMovieId(q.getMovieId());
        quizResponse.setMovieName(m.getName());
        quizResponse.setQuizNum(q.getQuizNum());
        quizResponse.setQuizBody(q.getQuizBody());

        return quizResponse;
    }
    @PostMapping("/{quizId}")
    public QuizCheckResponse checkAnswer(@RequestBody QuizRequest quizRequest, @PathVariable int quizId){
        int rightAnswer = quizService.checkQuizAnswer(quizRequest, quizId);
        if (rightAnswer == 1) {
            QuizCheckResponse quizCheckResponse = new QuizCheckResponse();
            quizCheckResponse.setQuizId(quizId);
            quizCheckResponse.setResultMessage("Correct Answer!");
            return quizCheckResponse;
        }
        else if (rightAnswer == 2) {
            QuizCheckResponse quizCheckResponse = new QuizCheckResponse();
            quizCheckResponse.setQuizId(quizId);
            quizCheckResponse.setResultMessage("Wrong Answer!");
            return quizCheckResponse;
        }
        else {
            QuizCheckResponse quizCheckResponse = new QuizCheckResponse();
            quizCheckResponse.setQuizId(quizId);
            quizCheckResponse.setResultMessage("You've already answered this quiz");
            return quizCheckResponse;
        }

    }
}
