package com.mindtrack.backend.treatmentPlan.domain.services;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetAllTreatmentPlanByPatientIdQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetAllTreatmentPlanByProfessionalIdQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetTreatmentPlanByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TreatmentPlanQueryService {
    Optional<TreatmentPlan> handle(GetTreatmentPlanByIdQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByPatientIdQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByProfessionalIdQuery query);
}
