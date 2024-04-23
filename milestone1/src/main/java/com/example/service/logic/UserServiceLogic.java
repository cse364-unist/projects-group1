package com.example.service.logic;

import com.example.entity.User;
import com.example.entity.cdo.UserRequest;
import com.example.service.UserService;
import com.example.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceLogic implements UserService{

    @Autowired
    UserStore userStore;

    @Override
    public User callUserById(int userId) {
        return userStore.callById(userId);
    }

    @Override
    public void modifyUser(UserRequest newUser, int userId) {
        User user = userStore.callById(userId);
        user.setPoint(newUser.getPoint());
        userStore.update(user);
    }
}
