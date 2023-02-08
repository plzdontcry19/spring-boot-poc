package com.demo.backend.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.demo.backend.entity.UserData;
import com.demo.backend.event.RegisterCompleteEvent;
import com.demo.backend.service.UserDataService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegisterCompleteEventListener implements ApplicationListener<RegisterCompleteEvent> {

    @Autowired
    private UserDataService userDataService;

    @Override
    public void onApplicationEvent(RegisterCompleteEvent event) {
        // create the verification token for the user with link
        UserData userData = event.getUserData();
        String token = UUID.randomUUID().toString();
        this.userDataService.saveVerificationTokenForUser(token, userData);
        // send mail to user
        String url = event.getAppUrl() + "/user-data/verify?token=" + token;

        log.info("Click the link to verify your account : {}", url);
    }

}
