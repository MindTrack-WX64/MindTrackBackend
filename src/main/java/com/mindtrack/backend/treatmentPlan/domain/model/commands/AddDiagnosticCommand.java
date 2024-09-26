package com.mindtrack.backend.treatmentPlan.domain.model.commands;

import java.time.LocalDate;

public record AddDiagnosticCommand(
        String name,
        String description,
        LocalDate date
) {
}
