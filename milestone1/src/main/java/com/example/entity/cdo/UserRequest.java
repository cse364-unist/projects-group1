package com.example.entity.cdo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private int userId;
    private int point;

    public UserRequest(int userId, int point){
        this.userId = userId;
        this.point = point;
    }
}
