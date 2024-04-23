package com.example.store.repository;

import com.example.entity.Movie;
import com.example.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String>{
    @Query("{ 'userId': { $in: ?0 } })")
    List<User> findByUserIdIn(List<Integer> userIds);
    Optional<User> findByUserId(int userId);
}
