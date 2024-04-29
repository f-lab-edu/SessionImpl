package com.example.sessionimpl.model.dto;

import com.example.sessionimpl.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
            .email(email)
            .password(password)
            .build();
    }
}
