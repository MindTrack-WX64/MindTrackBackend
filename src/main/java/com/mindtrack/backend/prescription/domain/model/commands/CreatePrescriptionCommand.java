package com.mindtrack.backend.prescription.domain.model.commands;

import java.time.LocalDate;

public record CreatePrescriptionCommand(
        String frequency,
        LocalDate startDate,
        LocalDate endDate
) {
}
