package com.demo.backend.service;

import java.util.List;
import com.demo.backend.entity.User;
import com.demo.backend.exception.NotFoundException;

public interface UserService {

    User craeteUser(User user);

    List<User> listUsers();

    User retriveUser(Long userId) throws NotFoundException;

    void deleteUser(Long userId);

    User updateUser(Long userId, User update);

    List<User> listUsersByName(String name);

}
