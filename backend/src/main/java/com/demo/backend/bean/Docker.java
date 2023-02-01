package com.demo.backend.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Docker {
    private String name;

    public void say(String world) {
        System.out.println("Hello " + world);
    }

    public int retrivePill() {
        return 2;
    }
}
