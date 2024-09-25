package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record CreateTreatmentPlanCommand(
        Long patientId,
        Long professionalId,
        String description
) {
}
