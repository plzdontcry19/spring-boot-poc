package com.demo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.backend.entity.User;
import com.demo.backend.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User user = User.builder()
                .age(1)
                .name("Helo")
                .userId(2L)
                .build();

        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.when(this.userRepository.findByName("Helo")).thenReturn(users);
    }

    @Test
    @DisplayName("Get Data based on Validate user name")
    // @Disabled
    public void whenValidUserName_thenUserShouldFound() {
        String userName = "Helo";
        List<User> users = this.userService.listUsersByName(userName);

        for (User user : users) {
            assertEquals(userName, user.getName());
        }
    }
}
