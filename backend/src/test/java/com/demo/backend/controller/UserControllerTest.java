package com.demo.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.backend.entity.User;
import com.demo.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private UserService userService;

        private User user;

        @BeforeEach
        public void setup() {
                this.user = User.builder()
                                .userId(1L)
                                .age(112)
                                .name("2ewe")
                                .build();
        }

        @Test
        void testCreateUser() throws Exception {
                User inputUser = User.builder()
                                .age(112)
                                .name("2ewe")
                                .build();

                Mockito.when(this.userService.createUser(inputUser)).thenReturn(this.user);

                this.mockMvc.perform(MockMvcRequestBuilders
                                .post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(inputUser)))
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }

        @Test
        void testListUsers() throws Exception {
                List<User> users = new ArrayList<User>();
                users.add(this.user);
                Mockito.when(this.userService.listUsers()).thenReturn(users);

                this.mockMvc.perform(MockMvcRequestBuilders
                                .get("/user")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers
                                                .jsonPath("$[0].name")
                                                .value(this.user.getName()));
        }

}
