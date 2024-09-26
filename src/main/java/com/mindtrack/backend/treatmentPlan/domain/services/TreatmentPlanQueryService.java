package com.mindtrack.backend.treatmentPlan.domain.services;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.*;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.TreatmentPlanStatistics;

import java.util.List;
import java.util.Optional;

public interface TreatmentPlanQueryService {
    Optional<TreatmentPlan> handle(GetTreatmentPlanByIdQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByPatientFullNameQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByProfessionalFullNameQuery query);
    /**List<TreatmentPlan> handle(GetAllTreatmentPlanBiologicalFunctionQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanDiagnosticQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanPatientStateQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanSessionQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanTaskQuery query);*/
    List<TreatmentPlanStatistics> handle(GetTreatmentPlanStatisticsDataQuery query);

}
