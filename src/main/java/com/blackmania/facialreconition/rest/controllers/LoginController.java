package com.blackmania.facialreconition.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(){
        return null;
    }

}
