package com.demo.backend.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.backend.entity.User;
import com.demo.backend.exception.NotFoundException;
import com.demo.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User retriveUser(Long userId) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NotFoundException();
        }
        return user.get();
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, User update) {
        User user = this.userRepository.findById(userId).get();

        if (Objects.nonNull(update.getName()) && !"".equalsIgnoreCase(update.getName())) {
            user.setName(update.getName());
        }

        if (Objects.nonNull(update.getAge())) {
            user.setAge(update.getAge());
        }

        return this.userRepository.save(user);
    }

    @Override
    public List<User> listUsersByName(String name) {
        return this.userRepository.findByName(name);
    }

}
