package com.example.sessionimpl.controller;

import com.example.sessionimpl.domain.dto.SignInForm;
import com.example.sessionimpl.domain.dto.SignUpForm;
import com.example.sessionimpl.service.SessionService;
import com.example.sessionimpl.service.SignUpService;
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

    private final SignUpService signUpService;
    private final SessionService sessionService;

    @PostMapping("/signup")
    public ResponseEntity<String> SignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpService.signUp(form));
    }

    @PostMapping("/login")
    public ResponseEntity<String> SignIn(@RequestBody SignInForm form){
        return ResponseEntity.ok(sessionService.userLogin(form));
    }

}
