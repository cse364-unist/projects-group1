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
public class QuizControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetQuiz() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("movieId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("movieName").value("Jumanji (1995)"))
                .andExpect(MockMvcResultMatchers.jsonPath("quizNum").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("quizBody").value("movie2 quiz1 Body"));
    }
    @Test
    public void testGetQuizFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/-6"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testPostQuizRightAndReset() throws Exception {
        //Reset user 2's quiz 4 status
        String jsonRequestBody1 = "{\"userId\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/reset/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(2));

        //Test for reset quiz status: no data for reset
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/reset/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("No data for requested quiz and user"));

        //Test for correct answer
        String jsonRequestBody2 = "{\"userId\":2,\"userAnswer\":1}";
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Correct Answer!"));

        //Test for reset quiz status: successfully reset
        String jsonRequestBody3 = "{\"userId\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/reset/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody3))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Successfully reset"));
    }

    @Test
    public void testPostQuizWrongAndReset() throws Exception {
        //Reset user 2's quiz 9 status
        String jsonRequestBody1 = "{\"userId\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/reset/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(2));

        //Test for reset quiz status: no data for reset
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/reset/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("No data for requested quiz and user"));

        //Test for wrong answer
        String jsonRequestBody2 = "{\"userId\":2,\"userAnswer\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Wrong Answer!"));

        //Test for reset quiz status: successfully reset
        String jsonRequestBody3 = "{\"userId\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/reset/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody3))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Successfully reset"));
    }
}
