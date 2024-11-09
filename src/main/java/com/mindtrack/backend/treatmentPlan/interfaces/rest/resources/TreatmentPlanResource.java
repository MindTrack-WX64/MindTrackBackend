package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record TreatmentPlanResource(
        Long treatmentPlanId,
        Long patientId,
        Long professionalId,
        LocalDate startDate,
        LocalDate endDate,
        boolean isFinished,
        String description,
        List<String> patientStates,
        List<String> biologicalFunctions,
        List<String> diagnostics,
        List<String> tasks
) {
}
