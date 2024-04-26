package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.User;

import com.example.entity.cdo.UserResponse;
import com.example.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public UserResponse findById(@PathVariable int userId){
        User u = userService.callUserById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(u.getUserId());
        userResponse.setPoint(u.getPoint());
        return userResponse;
    }
}
