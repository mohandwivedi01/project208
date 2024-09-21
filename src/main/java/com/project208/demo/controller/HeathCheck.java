package com.project208.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeathCheck {

    @GetMapping
    public String heathCheck(){
        return "I'm running...";
    }
}
