package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

public record FinishTaskResource(
        Long taskId,
        Long treatmentPlanId
) {
}
