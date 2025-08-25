package com.sivatechie.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivatechie.demo.model.User;
import com.sivatechie.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper; // provided by Spring Boot test auto-config

    @Test
    void testGetAllUsers() throws Exception {
        User u1 = new User(1L, "Siva", "siva@example.com");
        User u2 = new User(2L, "Ram", "ram@example.com");

        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Siva"))
                .andExpect(jsonPath("$[1].email").value("ram@example.com"));

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveUser() throws Exception {
        User input = new User((Long)null, "Siva", "siva@example.com");
        User saved = new User(1L, "Siva", "siva@example.com");

        when(userRepository.save(any(User.class))).thenReturn(saved);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Siva"));

        verify(userRepository, times(1)).save(any(User.class));
    }
}
