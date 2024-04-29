package com.example.sessionimpl.repository;

import com.example.sessionimpl.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Integer countByUserId(Long userId);
}
