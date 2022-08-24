package com.curady.userservice.repository;

import com.curady.userservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByUuid(String uuid);
}
