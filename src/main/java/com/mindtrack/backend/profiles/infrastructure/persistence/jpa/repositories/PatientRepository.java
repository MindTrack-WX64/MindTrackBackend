package com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
