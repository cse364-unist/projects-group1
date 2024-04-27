package com.example.service.logic;

import com.example.entity.Quiz;
import com.example.entity.User;
import com.example.entity.cdo.QuizRequest;
import com.example.entity.cdo.UserRequest;
import com.example.service.QuizService;
import com.example.store.QuizStore;
import com.example.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceLogic implements QuizService{

    @Autowired
    QuizStore quizStore;
    @Autowired
    UserStore userStore;

    @Override
    public Quiz callQuizById(int quizId) {
        return quizStore.callByQuizId(quizId);
    }

    @Override
    public int checkQuizAnswer(QuizRequest newQuizRequest, int quizId) {
        User user = userStore.callByUserId(newQuizRequest.getUserId());
        if (user.getQuizzes().containsKey(quizId)) {
            if (user.getQuizzes().get(quizId) != 0) {
                return 0;
            }
        }
        if (quizStore.callByQuizId(quizId).getQuizAnswer() == newQuizRequest.getUserAnswer()) {
            user.setPoint(user.getPoint() + 10);
            user.getQuizzes().put(quizId, 1);
            userStore.update(user);
            return 1;
        }
        else {
            user.getQuizzes().put(quizId, 2);
            userStore.update(user);
            return 2;
        }
    }

    @Override
    public boolean resetQuizStatus(UserRequest newUserRequest, int quizId) {
        User user = userStore.callByUserId(newUserRequest.getUserId());
        if (user.getQuizzes().containsKey(quizId)) {
            user.getQuizzes().remove(quizId);
            userStore.update(user);
            return true;
        }
        else {
            return false;
        }
    }
}
