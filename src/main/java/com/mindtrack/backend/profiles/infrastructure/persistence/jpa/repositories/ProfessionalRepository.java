package com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}
