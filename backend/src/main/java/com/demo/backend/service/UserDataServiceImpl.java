package com.demo.backend.service;

import com.demo.backend.dto.RegisterDTO;
import com.demo.backend.entity.PasswordResetToken;
import com.demo.backend.entity.UserData;
import com.demo.backend.entity.VerifyToken;
import com.demo.backend.repository.PasswordResetTokenRepository;
import com.demo.backend.repository.UserDataRepository;
import com.demo.backend.repository.VerifyTokenRepository;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private VerifyTokenRepository verifyTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserData register(RegisterDTO model) {

        UserData userData = UserData.builder()
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .email(model.getEmail())
                .password(this.passwordEncoder.encode(model.getPassword()))
                .role("USER")
                .build();

        return this.userDataRepository.save(userData);
    }

    @Override
    public void saveVerificationTokenForUser(String token, UserData userData) {
        VerifyToken verifyToken = new VerifyToken(userData, token);
        this.verifyTokenRepository.save(verifyToken);
    }

    @Override
    public String validateToken(String token) {

        VerifyToken verifyToken = this.verifyTokenRepository.findByToken(token);
        if (verifyToken == null) {
            return "invalid";
        }

        UserData user = verifyToken.getUserData();

        Calendar calendar = Calendar.getInstance();
        if (calendar.getTime().getTime() > verifyToken.getExpirationTime().getTime()) {
            this.verifyTokenRepository.delete(verifyToken);
            return "expired";
        }

        user.setEnabled(true);
        this.userDataRepository.save(user);
        return "valid";
    }

    @Override
    public VerifyToken generateNewVerifyToken(String oldToken) {
        VerifyToken verifyToken = this.verifyTokenRepository.findByToken(oldToken);
        verifyToken.setToken(UUID.randomUUID().toString());
        return this.verifyTokenRepository.save(verifyToken);
    }

    @Override
    public UserData findUserByEmail(String email) {
        return userDataRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(UserData userData, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(userData, token);
        this.passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordToken = this.passwordResetTokenRepository.findByToken(token);
        if (passwordToken == null) {
            return "invalid";
        }

        UserData user = passwordToken.getUserData();
        Calendar calendar = Calendar.getInstance();
        if (calendar.getTime().getTime() > passwordToken.getExpirationTime().getTime()) {
            this.passwordResetTokenRepository.delete(passwordToken);
            return "expired";
        }

        user.setEnabled(true);
        this.userDataRepository.save(user);
        return "valid";
    }

    @Override
    public Optional<UserData> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(this.passwordResetTokenRepository.findByToken(token).getUserData());
    }

    @Override
    public void changePassword(UserData userData, String password) {
        userData.setPassword(this.passwordEncoder.encode(password));
        this.userDataRepository.save(userData);
    }

    @Override
    public boolean checkIfValidOldPassword(UserData user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

}
