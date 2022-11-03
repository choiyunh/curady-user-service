package com.curady.userservice.domain.tendency.repository;

import com.curady.userservice.domain.tendency.model.Tendency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TendencyRepository extends CrudRepository<Tendency, Long> {
    Optional<Tendency> findByNameAndType(String name, String type);
}
