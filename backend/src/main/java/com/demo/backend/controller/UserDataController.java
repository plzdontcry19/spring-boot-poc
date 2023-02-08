package com.demo.backend.controller;

import com.demo.backend.entity.User;
import com.demo.backend.entity.UserData;
import com.demo.backend.entity.VerifyToken;
import com.demo.backend.event.RegisterCompleteEvent;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.dto.ChangePasswordDTO;
import com.demo.backend.dto.RegisterDTO;
import com.demo.backend.dto.ResetPasswordDTO;
import com.demo.backend.dto.SavePasswordDTO;
import com.demo.backend.service.UserDataService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/user-data")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/verify")
    public String verfiyToken(@RequestParam("token") String token) {
        String result = userDataService.validateToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User Verfies Successfully";
        }
        return "Bad user";
    }

    @GetMapping("/resend-verify")
    public String resendVerifyToken(@RequestParam("token") String oldToken, final HttpServletRequest request) {
        VerifyToken verifyToken = this.userDataService.generateNewVerifyToken(oldToken);
        UserData userData = verifyToken.getUserData();
        resendVerifyTokenEmail(userData, getAppUrl(request), verifyToken);
        return "Verify Link Sent";
    }

    @PostMapping("/register")
    public void createUser(@Valid @RequestBody RegisterDTO body, final HttpServletRequest request) {
        UserData userData = this.userDataService.register(body);

        publisher.publishEvent(
                RegisterCompleteEvent.builder()
                        .userData(userData)
                        .appUrl(getAppUrl(request))
                        .build());
    }

    @PostMapping("/reset")
    public String resetPassword(@Valid @RequestBody ResetPasswordDTO body, final HttpServletRequest request) {
        UserData userData = this.userDataService.findUserByEmail(body.getEmail());
        String url = "";
        if (userData != null) {
            String token = UUID.randomUUID().toString();
            this.userDataService.createPasswordResetTokenForUser(userData, token);
            url = passwordResetTokenMail(userData, getAppUrl(request), token);
        }
        return url;
    }

    @PostMapping("/save-password")
    public String savePassword(@Valid @RequestBody SavePasswordDTO body, @RequestParam("token") String token) {
        String result = this.userDataService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            return "Invalid Token";
        }
        Optional<UserData> userData = this.userDataService.getUserByPasswordResetToken(token);
        if (userData.isPresent()) {
            this.userDataService.changePassword(userData.get(), body.getPassword());
            return "Password Reset successfully";
        }
        return "Invalid Token";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @RequestBody ChangePasswordDTO body) {
        UserData user = this.userDataService.findUserByEmail(body.getEmail());

        if (!this.userDataService.checkIfValidOldPassword(user, body.getOldPassword())) {
            return "Invalid Old Password";
        }
        this.userDataService.changePassword(user, body.getNewPassword());
        return "Pasword Changed Scuessfully";
    }

    private String passwordResetTokenMail(UserData userData, String appUrl, String token) {
        String url = appUrl + "/user-data/save-password?token=" + token;
        // send email
        log.info("Click the link to reset your password : {}", url);
        return url;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort();
    }

    private void resendVerifyTokenEmail(UserData userData, String appUrl, VerifyToken verifyToken) {
        String url = appUrl + "/user-data/verify?token=" + verifyToken;
        // send email
        log.info("Click the link to verify your account : {}", url);
    }

}
