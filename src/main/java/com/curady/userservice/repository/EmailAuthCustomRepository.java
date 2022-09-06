package com.curady.userservice.repository;

import com.curady.userservice.entity.EmailAuth;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthCustomRepository {
    Optional<EmailAuth> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime);
}

