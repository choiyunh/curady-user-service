package com.curady.userservice.domain.repository;

import com.curady.userservice.domain.entity.Tendency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TendencyRepository extends CrudRepository<Tendency, Long> {
    Optional<Tendency> findByName(String name);
}
