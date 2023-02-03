package com.demo.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.entity.User;
import com.demo.backend.exception.NotFoundException;
import com.demo.backend.service.UserService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public List<User> listUsers() {
        return this.userService.listUsers();
    }

    @GetMapping("/{id}")
    public User retriveUser(@PathVariable("id") Long userId) throws NotFoundException {
        return this.userService.retriveUser(userId);
    }

    @GetMapping("/name/{name}")
    public List<User> retriveUser(@PathVariable("name") String name) {
        return this.userService.listUsersByName(name);
    }

    @PostMapping("")
    public User createUser(@Valid @RequestBody User body) {
        // this.logger.info("Create user body: " + body.toString());
        // return body;
        return this.userService.createUser(body);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        this.userService.deleteUser(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long userId, @RequestBody User body) {
        return this.userService.updateUser(userId, body);
    }
}
