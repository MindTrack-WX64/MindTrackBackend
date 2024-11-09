package com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.shared.domain.valueobjects.TreatmentPlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByTreatmentPlanId(TreatmentPlanId treatmentPlanId);
    List<Prescription> findByProfessionalId(Long professionalId);
}
