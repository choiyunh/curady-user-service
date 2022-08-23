package com.curady.userservice.repository;

import com.curady.userservice.model.Tendency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TendencyRepository extends CrudRepository<Tendency, Long> {
    Optional<Tendency> findByName(String name);
}
