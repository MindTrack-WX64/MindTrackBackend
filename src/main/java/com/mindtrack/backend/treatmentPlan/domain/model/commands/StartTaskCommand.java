package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record StartTaskCommand(
        Long taskId,
        Long treatmentPlanId
) {
}
