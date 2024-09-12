package com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsById(Long id);

    Optional<Patient> findById(Long id);

    List<Patient> findAll();
}
