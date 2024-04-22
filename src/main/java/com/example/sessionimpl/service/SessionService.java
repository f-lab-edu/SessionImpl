package com.example.sessionimpl.service;

import com.example.sessionimpl.domain.Session;
import com.example.sessionimpl.domain.User;
import com.example.sessionimpl.domain.dto.SessionForm;
import com.example.sessionimpl.domain.dto.SignInForm;
import com.example.sessionimpl.domain.repository.SessionRepository;
import com.example.sessionimpl.domain.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private long validTime = 1000L * 60 * 60;

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
        LocalDateTime now = LocalDateTime.now();
        SessionForm build = SessionForm.builder()
            .userId(user.getId())
            .key(key.toString())
            .inValidTime(now.plusHours(1))
            .build();
        sessionRepository.save(Session.from(build));
        return key.toString();
    }
}
