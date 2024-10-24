package com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Long> {
    Optional<ClinicalHistory> findById(Long id);
    Optional<ClinicalHistory> findByPatientId(Long patientId);
    List<ClinicalHistory> findAll();
}
