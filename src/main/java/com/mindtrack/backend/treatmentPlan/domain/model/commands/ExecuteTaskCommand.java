package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record ExecuteTaskCommand(
        String action,
        Long taskId,
        Long treatmentPlanId
) {
}
