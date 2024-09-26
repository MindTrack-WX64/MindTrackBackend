package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import java.time.LocalDate;

public record AddBiologicalFunctionResource(
        LocalDate date,
        int hunger,
        int sleep,
        int hydration,
        int energy,
        Long treatmentPlanId
) {
}
