package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import java.time.LocalDate;

public record AddDiagnosticResource(
        String name,
        String description,
        LocalDate date,
        Long treatmentPlanId
) {
}
