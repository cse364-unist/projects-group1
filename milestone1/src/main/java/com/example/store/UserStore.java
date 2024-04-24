package com.example.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
import com.example.store.repository.UserRepository;

@Repository
public class UserStore {
    @Autowired
    UserRepository userRepository;

    public User callbyUserId(int userId){
        return userRepository.findByUserId(userId).get();
    }
}
