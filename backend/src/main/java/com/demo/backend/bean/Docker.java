package com.demo.backend.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component("helo")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Docker {
    private String name; 
}
