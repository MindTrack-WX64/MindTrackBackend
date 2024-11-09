package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

public record AddBiologicalFunctionResource(
        int hunger,
        int sleep,
        int hydration,
        int energy
) {
}
