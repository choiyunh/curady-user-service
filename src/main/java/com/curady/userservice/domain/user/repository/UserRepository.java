package com.curady.userservice.domain.user.repository;

import com.curady.userservice.domain.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmailAndProvider(String email, String provider);

    Optional<User> findByRefreshToken(String refreshToken);
}
