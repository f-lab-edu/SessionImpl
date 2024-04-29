package com.example.sessionimpl.controller;

import com.example.sessionimpl.model.dto.SignInForm;
import com.example.sessionimpl.model.dto.SignUpForm;
import com.example.sessionimpl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> SignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(userService.signUp(form));
    }

    @PostMapping("/login")
    public ResponseEntity<String> SignIn(@RequestBody SignInForm form){
        return ResponseEntity.ok(userService.userLogin(form));
    }

}
