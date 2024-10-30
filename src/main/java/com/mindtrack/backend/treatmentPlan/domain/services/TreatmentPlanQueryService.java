package com.mindtrack.backend.treatmentPlan.domain.services;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.*;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.TreatmentPlanStatistics;

import java.util.List;
import java.util.Optional;

public interface TreatmentPlanQueryService {
    Optional<TreatmentPlan> handle(GetTreatmentPlanByIdQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByPatientIdQuery query);
    List<TreatmentPlan> handle(GetAllTreatmentPlanByProfessionalIdQuery query);
    List<TreatmentPlanStatistics> handle(GetTreatmentPlanStatisticsDataQuery query);

}
