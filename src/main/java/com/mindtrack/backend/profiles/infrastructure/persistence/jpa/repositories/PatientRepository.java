package com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUserId(Long userId);
    @Query("SELECT p FROM Patient p WHERE p.professionalId= :professionalId")
    List<Patient> findByProfessionalId(@Param("professionalId") Long professionalId);
}
