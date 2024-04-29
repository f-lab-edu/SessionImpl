package com.example.sessionimpl.model.dto;

import com.example.sessionimpl.model.Session;
import java.time.LocalDateTime;
import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionForm {
    private Long userId;
    private String key;
    private LocalDateTime expiresAt;

    public Session toEntity(){
        return Session.builder()
            .userId(userId)
            .key(key)
            .expiresAt(expiresAt)
            .build();
    }
}
