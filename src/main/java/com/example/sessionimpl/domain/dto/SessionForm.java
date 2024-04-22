package com.example.sessionimpl.domain.dto;

import java.time.LocalDateTime;
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
    private LocalDateTime inValidTime;
}
