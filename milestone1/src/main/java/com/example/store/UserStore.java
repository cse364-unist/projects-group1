package com.example.store;

import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserStore {
    @Autowired
    UserRepository userRepository;

    public int create(User newUser){
        userRepository.save(newUser);
        return newUser.getUserId();
    }

    public User callById(int userId){
        Optional<User> user = userRepository.findByUserId(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException(userId);
        }
        return user.get();
    }

    public void update(User newUser){
        userRepository.save(newUser);
    }
}
