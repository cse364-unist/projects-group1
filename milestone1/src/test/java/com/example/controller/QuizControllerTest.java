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
    public void testPostQuizCorrectAnswer() throws Exception {
        // JSON 요청 본문
        String jsonRequestBody = "{\"userId\":2,\"userAnswer\":1}";

        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 추가적으로 응답 본문의 검증도 가능
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Correct Answer!"));
    }
    @Test
    public void testPostQuizWrongAnswer() throws Exception {
        // JSON 요청 본문
        String jsonRequestBody = "{\"userId\":2,\"userAnswer\":2}";

        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 추가적으로 응답 본문의 검증도 가능
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Wrong Answer!"));
    }
    @Test
    public void testPostQuizAlreadySolved() throws Exception {
        // JSON 요청 본문
        String jsonRequestBody = "{\"userId\":2,\"userAnswer\":1}";

        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 추가적으로 응답 본문의 검증도 가능
                .andExpect(MockMvcResultMatchers.jsonPath("quizId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("You already solved this quiz"));
    }
}
