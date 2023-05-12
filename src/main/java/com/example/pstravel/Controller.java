package com.example.pstravel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Controller {
    @GetMapping("/health")
    public String hello(){
        return "HELLO , WORLD";
    }
}
