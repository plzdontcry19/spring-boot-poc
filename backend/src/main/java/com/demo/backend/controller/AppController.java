package com.demo.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@RestController

public class AppController {

    @Value("${server.port}")

    @GetMapping(value = "/")
    public String index() {
        return "demo1";
    }

}
