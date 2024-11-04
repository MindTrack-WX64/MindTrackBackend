package com.mindtrack.backend.treatmentPlan.application.internal.queryservices;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.*;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.TreatmentPlanStatistics;
import com.mindtrack.backend.treatmentPlan.domain.services.TreatmentPlanQueryService;
import com.mindtrack.backend.treatmentPlan.infrastructure.persistence.jpa.repositories.TreatmentPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentPlanQueryServiceImpl implements TreatmentPlanQueryService {
    private final TreatmentPlanRepository treatmentPlanRepository;

    public TreatmentPlanQueryServiceImpl(TreatmentPlanRepository treatmentPlanRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    @Override
    public Optional<TreatmentPlan> handle(GetTreatmentPlanByIdQuery query) {
        return this.treatmentPlanRepository.findById(query.treatmentPlanId());
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanByPatientIdQuery query) {
        return this.treatmentPlanRepository.getAllByPatientId(query.patientId());
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanByProfessionalIdQuery query) {
        return this.treatmentPlanRepository.getAllByProfessionalId(query.professionalId());
    }
}
