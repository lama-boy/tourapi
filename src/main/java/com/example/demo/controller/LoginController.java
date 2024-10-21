package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.UserDTO;

@RestController
public class LoginController {

    @PostMapping("/login.do")
    public ResponseEntity<UserDTO> login(UserDTO userInfo) {
        System.out.println(userInfo.toString());
        return ResponseEntity.ok(userInfo);
    }

}
