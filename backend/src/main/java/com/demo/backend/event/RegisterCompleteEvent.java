package com.demo.backend.event;

import org.springframework.context.ApplicationEvent;
import com.demo.backend.entity.UserData;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterCompleteEvent extends ApplicationEvent {

    private UserData userData;
    private String appUrl;

    public RegisterCompleteEvent(UserData userData, String appUrl) {
        super(userData);
        this.userData = userData;
        this.appUrl = appUrl;
    }

}