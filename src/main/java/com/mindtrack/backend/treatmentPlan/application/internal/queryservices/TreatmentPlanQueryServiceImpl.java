package com.mindtrack.backend.treatmentPlan.application.internal.queryservices;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.*;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.TreatmentPlanStatistics;
import com.mindtrack.backend.treatmentPlan.domain.services.TreatmentPlanQueryService;
import com.mindtrack.backend.treatmentPlan.infrastructure.persistence.jpa.repositories.TreatmentPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentPlanQueryServiceImpl implements TreatmentPlanQueryService {
    private final TreatmentPlanRepository treatmentPlanRepository;

    public TreatmentPlanQueryServiceImpl(TreatmentPlanRepository treatmentPlanRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanBiologicalFunctionQuery query) {
        return this.treatmentPlanRepository.getAllTreatmentPlanBiologicalFunctions();
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanByPatientFullNameQuery query) {
        return this.treatmentPlanRepository.getAllByPatientFullName(query.patientFullName());
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanByProfessionalFullNameQuery query) {
        return this.treatmentPlanRepository.getAllByProfessionalFullName(query.professionalFullName());
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanDiagnosticQuery query) {
        return this.treatmentPlanRepository.getAllTreatmentPlanDiagnostics();
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanPatientStateQuery query) {
        return this.treatmentPlanRepository.getAllTreatmentPlanPatientStates();
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanSessionQuery query) {
        return this.treatmentPlanRepository.getAllTreatmentPlanSessions();
    }

    @Override
    public List<TreatmentPlan> handle(GetAllTreatmentPlanTaskQuery query) {
        return this.treatmentPlanRepository.getAllTreatmentPlanTasks();
    }

    @Override
    public List<TreatmentPlanStatistics> handle(GetTreatmentPlanStatisticsDataQuery query) {
        return this.treatmentPlanRepository.getTreatmentPlanBiologicalFunctionsStatistics();
    }
}
