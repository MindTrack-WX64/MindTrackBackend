package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

public record AddSessionResource(
        Long sessionId,
        Long treatmentPlanId
) {
}
