package com.example.controller;

import com.example.entity.Quiz;
import com.example.entity.Movie;
import com.example.entity.User;
import com.example.entity.cdo.*;
import com.example.service.MovieService;
import com.example.service.UserService;
import com.example.service.QuizService;
import com.example.store.UserStore;
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
        QuizCheckResponse quizCheckResponse = new QuizCheckResponse();
        quizCheckResponse.setQuizId(quizId);
        quizCheckResponse.setUserId(quizRequest.getUserId());
        if (rightAnswer == 1) {
            quizCheckResponse.setResultMessage("Correct Answer!");
        }
        else if (rightAnswer == 2) {
            quizCheckResponse.setResultMessage("Wrong Answer!");
        }
        else {
            quizCheckResponse.setResultMessage("You've already answered this quiz");
        }
        return quizCheckResponse;
    }
    @PostMapping("/reset/{quizId}")
    public QuizCheckResponse resetStatus(@RequestBody UserRequest userRequest, @PathVariable int quizId) {
        boolean resetStatus = quizService.resetQuizStatus(userRequest, quizId);
        QuizCheckResponse quizCheckResponse = new QuizCheckResponse();
        quizCheckResponse.setQuizId(quizId);
        quizCheckResponse.setUserId(userRequest.getUserId());
        if (resetStatus) {
            quizCheckResponse.setResultMessage("Successfully reset");
        }
        else {
            quizCheckResponse.setResultMessage("No data for requested quiz and user");
        }
        return quizCheckResponse;
    }
}
