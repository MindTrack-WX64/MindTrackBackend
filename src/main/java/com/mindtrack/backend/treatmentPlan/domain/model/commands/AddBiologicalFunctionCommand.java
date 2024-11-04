package com.mindtrack.backend.treatmentPlan.domain.model.commands;

import java.time.LocalDate;

public record AddBiologicalFunctionCommand(
        int hunger,
        int sleep,
        int hydration,
        int energy,
        Long treatmentPlanId
) {
    public AddBiologicalFunctionCommand {
        if (hunger < 1 || hunger > 10) {
            throw new IllegalArgumentException("Hunger must be between 1 and 10");
        }
        if (sleep < 1 || sleep > 10) {
            throw new IllegalArgumentException("Sleep must be between 1 and 10");
        }
        if (hydration < 1 || hydration > 10) {
            throw new IllegalArgumentException("Hydration must be between 1 and 10");
        }
        if (energy < 1 || energy > 10) {
            throw new IllegalArgumentException("Energy must be between 1 and 10");
        }
    }
}
