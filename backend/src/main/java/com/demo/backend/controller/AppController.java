package com.demo.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/")
public class AppController {

    @Value("${welcome.message}")
    private String message;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping()
    public String index() {
        return this.message + " " + this.serverPort;
    }

}
