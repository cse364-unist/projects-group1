package com.example.service;

import com.example.entity.User;
import com.example.entity.cdo.UserRequest;


public interface UserService {

    User callUserById(int userId);
    void modifyUser(UserRequest newUser, int userId);
}
