package com.mindtrack.backend.treatmentPlan.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
    List<TreatmentPlan> getAllByPatientId(Long patientId);
    List<TreatmentPlan> getAllByProfessionalId(Long professionalId);
}
