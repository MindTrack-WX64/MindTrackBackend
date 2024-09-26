package com.mindtrack.backend.treatmentPlan.domain.services;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.*;

import java.util.List;

public interface TreatmentPlanQueryService {
    List<TreatmentPlan> handle(GetAllTreatmentPlanBiologicalFunctionQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByPatientFullNameQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByProfessionalFullNameQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanDiagnosticQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanPatientStateQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanSessionQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanTaskQuery query);
    List<TreatmentPlan> handle(GetTreatmentPlanStatisticsDataQuery query);

}
