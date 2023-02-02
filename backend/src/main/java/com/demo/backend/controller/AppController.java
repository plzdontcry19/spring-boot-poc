package com.demo.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController()
public class AppController {

    @GetMapping("/")
    public String index() {
        return "demo1";
    }

}
