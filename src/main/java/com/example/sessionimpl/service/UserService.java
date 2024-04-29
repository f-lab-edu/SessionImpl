package com.example.sessionimpl.service;

import com.example.sessionimpl.model.User;
import com.example.sessionimpl.model.dto.SessionForm;
import com.example.sessionimpl.model.dto.SignInForm;
import com.example.sessionimpl.model.dto.SignUpForm;
import com.example.sessionimpl.repository.SessionRepository;
import com.example.sessionimpl.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public String signUp(SignUpForm form){
        userRepository.save(form.toEntity());
        return "회원가입 완료";
    }
    public Optional<User> findValidUser(String email, String password){
        return userRepository.findByEmail(email)
            .filter(user -> user.getPassword().equals(password))
            .stream().findFirst();
    }
    public String userLogin(SignInForm form){
        User user = findValidUser(form.getEmail(),form.getPassword())
            .orElseThrow(RuntimeException::new);
        Integer count = sessionRepository.countByUserId(user.getId());
        if(count >= 3) return "세션 연결 갯수 초과";

        UUID key = UUID.randomUUID();
        LocalDateTime now = new Date()
            .toInstant()
            .atZone(ZoneOffset.of("+09:00"))
            .toLocalDateTime();
        SessionForm build = SessionForm.builder()
            .userId(user.getId())
            .key(key.toString())
            .expiresAt(now.plusHours(1))
            .build();
        sessionRepository.save(build.toEntity());
        return key.toString();
    }
}
