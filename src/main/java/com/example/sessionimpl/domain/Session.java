package com.example.sessionimpl.domain;

import com.example.sessionimpl.domain.dto.SessionForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String sessionKey;
    private LocalDateTime inValidTime;

    public static Session from(SessionForm form){
        return Session.builder()
            .userId(form.getUserId())
            .sessionKey(form.getKey())
            .inValidTime(form.getInValidTime())
            .build();
    }
}
