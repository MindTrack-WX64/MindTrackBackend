package com.mindtrack.backend.shared.domain.valueobjects;

public record TreatmentPlanId(Long treatmentPlanId) {
    public TreatmentPlanId {
        if (treatmentPlanId == null || treatmentPlanId < 1) {
            throw new IllegalArgumentException("TreatmentPlanId cannot be null or less than 1");
        }
    }
}
