package com.curady.userservice.repository;

import com.curady.userservice.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long>, EmailAuthCustomRepository {
}
