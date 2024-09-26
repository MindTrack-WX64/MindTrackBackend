package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

public record CreateTreatmentPlanResource(
        Long patientId,
        Long professionalId,
        String description
) {
}
