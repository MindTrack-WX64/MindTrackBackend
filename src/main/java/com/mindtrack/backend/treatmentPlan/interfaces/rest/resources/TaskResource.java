package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import java.time.LocalDate;

public record TaskResource(
        Long id,
        Long treatmentPlanId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        boolean status
) {
}
