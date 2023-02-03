package com.demo.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.demo.backend.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User user = User.builder()
                // .userId(1L)
                .age(112)
                .name("2ewe")
                .build();

        entityManager.persist(user);
    }

    @Test
    public void whenFindById_thenFoundThisId() {
        List<User> users = this.userRepository.findByName("2ewe");
        assertNotEquals(users.size(), 0);
        for (User user : users) {
            assertEquals("2ewe", user.getName());
        }
    }
}
