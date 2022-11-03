package com.curady.userservice.domain.emailAuth.repository;

import com.curady.userservice.domain.emailAuth.model.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long>, EmailAuthCustomRepository {
}
