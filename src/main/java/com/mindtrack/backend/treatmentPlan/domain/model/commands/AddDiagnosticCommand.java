package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record AddDiagnosticCommand(
        String name,
        String description,
        Long treatmentPlanId
) {
}
