package com.curady.userservice.domain.userTendency.repository;


import com.curady.userservice.domain.tendency.model.Tendency;
import com.curady.userservice.domain.user.model.User;
import com.curady.userservice.domain.userTendency.model.UserTendency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTendencyRepository extends CrudRepository<UserTendency, Long> {
    Optional<UserTendency> findByUserAndTendency(User user, Tendency tendency);

    void deleteAllByUser(User user);
}
