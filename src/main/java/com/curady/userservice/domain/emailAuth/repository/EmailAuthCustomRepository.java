package com.curady.userservice.domain.emailAuth.repository;

import com.curady.userservice.domain.emailAuth.model.EmailAuth;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthCustomRepository {
    Optional<EmailAuth> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime);
}

