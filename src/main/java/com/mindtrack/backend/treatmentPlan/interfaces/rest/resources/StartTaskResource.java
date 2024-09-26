package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

public record StartTaskResource(
        Long taskId,
        Long treatmentPlanId
) {
}
