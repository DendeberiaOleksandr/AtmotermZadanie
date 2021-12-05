package com.dendeberia.demo.repository;

import com.dendeberia.demo.model.Parametr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametrRepository extends JpaRepository<Parametr, Long> {
    Optional<Parametr> findById(Parametr parametr);
}
