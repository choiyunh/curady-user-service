package com.curady.userservice.domain.repository;


import com.curady.userservice.domain.entity.Tendency;
import com.curady.userservice.domain.entity.User;
import com.curady.userservice.domain.entity.UserTendency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTendencyRepository extends CrudRepository<UserTendency, Long> {
    Optional<UserTendency> findByUserAndTendency(User user, Tendency tendency);
}
