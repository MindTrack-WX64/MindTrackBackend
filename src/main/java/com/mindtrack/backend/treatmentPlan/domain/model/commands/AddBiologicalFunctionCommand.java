package com.mindtrack.backend.treatmentPlan.domain.model.commands;

import java.time.LocalDate;

public record AddBiologicalFunctionCommand(
        LocalDate date,
        int hunger,
        int sleep,
        int hydration,
        int energy,
        Long treatmentPlanId
) {
}
