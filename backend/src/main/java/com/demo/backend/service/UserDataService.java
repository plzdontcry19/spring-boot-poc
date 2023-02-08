package com.demo.backend.service;

import java.util.Optional;

import com.demo.backend.dto.RegisterDTO;
import com.demo.backend.entity.UserData;
import com.demo.backend.entity.VerifyToken;

public interface UserDataService {

    UserData register(RegisterDTO model);

    void saveVerificationTokenForUser(String token, UserData userData);

    String validateToken(String token);

    VerifyToken generateNewVerifyToken(String oldToken);

    UserData findUserByEmail(String email);

    void createPasswordResetTokenForUser(UserData userData, String token);

    String validatePasswordResetToken(String token);

    Optional<UserData> getUserByPasswordResetToken(String token);

    void changePassword(UserData userData, String string);

    boolean checkIfValidOldPassword(UserData user, String oldPassword);

}
