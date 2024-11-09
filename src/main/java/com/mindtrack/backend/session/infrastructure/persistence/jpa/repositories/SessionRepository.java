package com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.shared.domain.valueobjects.TreatmentPlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByProfessionalId(Long professionalId);
    List<Session> findAllByTreatmentPlanId(TreatmentPlanId treatmentPlanId);
}
