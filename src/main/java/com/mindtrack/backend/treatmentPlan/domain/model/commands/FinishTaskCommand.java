package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record FinishTaskCommand(
        Long taskId,
        Long treatmentPlanId
) {
}
