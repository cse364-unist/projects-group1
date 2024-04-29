package com.example.service.logic;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceLogic implements UserService{

    @Autowired
    UserStore userStore;

    @Override
    public User callUserById(int userId) {
        return userStore.callByUserId(userId);
    }
}
