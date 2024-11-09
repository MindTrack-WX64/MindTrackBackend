package com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

}
