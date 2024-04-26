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

    public User callByUserId(int userId){
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException(userId);
        }
        return userOptional.get();
    }

    public void update(User newUser){
        userRepository.save(newUser);
    }
}
