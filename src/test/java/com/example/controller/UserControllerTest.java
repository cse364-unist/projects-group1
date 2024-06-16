package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // @Test
    // public void testGetUser() throws Exception {
    //     mockMvc.perform(MockMvcRequestBuilders.get("/users/11"))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("userId").value(11))
    //             .andExpect(MockMvcResultMatchers.jsonPath("point").value(93));
    // }
    @Test
    public void testGetUserFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/-6"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
