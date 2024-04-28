package com.example.entity.cdo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private int userId;

    public UserRequest(int userId){
        this.userId = userId;
    }
}
