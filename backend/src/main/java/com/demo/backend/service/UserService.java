package com.demo.backend.service;

import java.util.List;
import com.demo.backend.entity.User;

public interface UserService {

    User craeteUser(User user);

    List<User> listUsers();

    User retriveUser(Long userId);

    void deleteUser(Long userId);

    User updateUser(Long userId, User update);

    List<User> listUsersByName(String name);

}
