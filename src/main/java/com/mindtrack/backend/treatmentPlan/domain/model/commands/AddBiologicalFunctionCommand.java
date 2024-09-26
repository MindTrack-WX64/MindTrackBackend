package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record AddBiologicalFunctionCommand(
        int hunger,
        int sleep,
        int hydration,
        int energy
) {
}
