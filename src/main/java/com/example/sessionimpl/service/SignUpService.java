package com.example.sessionimpl.service;

import com.example.sessionimpl.domain.User;
import com.example.sessionimpl.domain.dto.SignUpForm;
import com.example.sessionimpl.domain.repository.UserRepository;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;

    public String signUp(SignUpForm form){
        userRepository.save(User.from(form));
        return "회원가입 완료";
    }
}
