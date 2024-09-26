package com.mindtrack.backend.treatmentPlan.domain.model.valuobjects;

public interface TreatmentPlanStatistics {
    Long getTreatmentPlanId();
    Double getAverageHunger();
    Double getAverageSleep();
    Double getAverageHydration();
}
