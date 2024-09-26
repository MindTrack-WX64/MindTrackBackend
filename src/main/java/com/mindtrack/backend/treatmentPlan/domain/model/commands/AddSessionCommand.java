package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record AddSessionCommand(
        Long sessionId,
        Long treatmentPlanId
) {
}
