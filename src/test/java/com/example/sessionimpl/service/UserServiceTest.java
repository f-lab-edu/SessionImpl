package com.example.sessionimpl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.sessionimpl.model.Session;
import com.example.sessionimpl.model.User;
import com.example.sessionimpl.model.dto.SignInForm;
import com.example.sessionimpl.model.dto.SignUpForm;
import com.example.sessionimpl.repository.SessionRepository;
import com.example.sessionimpl.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private UserService userService;

    private final User user = User.builder()
        .id(1L)
        .email("aaa@naver.com")
        .password("1111").build();

    @Test
    void signUp() {
        //given
        SignUpForm form = SignUpForm.builder()
            .email("aaa@naver.com")
            .password("1111").build();

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        given(userRepository.save(any()))
            .willReturn(user);
        //when
        userService.signUp(form);
        //then
        verify(userRepository, times(1))
            .save(captor.capture());
        User savedUser = captor.getValue();
        assertEquals(form.getEmail(), savedUser.getEmail());
        assertEquals(form.getPassword(), savedUser.getPassword());
    }

    @Test
    void findValidUser() {
        //given
        given(userService.findValidUser("aaa@naver.com", "1111"))
            .willReturn(Optional.ofNullable(user));
        //when
        User validUser = userService.findValidUser("aaa@naver.com", "1111")
            .orElseThrow();
        //then
        assertEquals(user.getEmail(), validUser.getEmail());
        assertEquals(user.getPassword(), validUser.getPassword());
    }

    @Test
    void userLogin() {
        //given
        SignInForm form = SignInForm.builder()
            .email("aaa@naver.com")
            .password("1111").build();
        given(userService.findValidUser("aaa@naver.com", "1111"))
            .willReturn(Optional.ofNullable(user));
        given(sessionRepository.countByUserId(any()))
            .willReturn(0);
        LocalDateTime now = new Date()
            .toInstant()
            .atZone(ZoneId.of("Asia/Seoul"))
            .toLocalDateTime();
        ArgumentCaptor<Session> captor = ArgumentCaptor.forClass(Session.class);
        //when
        userService.userLogin(form);
        //then
        verify(sessionRepository, times(1))
            .save(captor.capture());
        Session session = captor.getValue();
        assertEquals(now.plusHours(1).getHour(), session.getExpiresAt().getHour());
        assertEquals(user.getId(), 1L);
    }
}