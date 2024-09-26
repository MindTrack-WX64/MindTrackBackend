package com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.session.domain.model.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findById(Long id);
    List<Professional> findAll();
}
