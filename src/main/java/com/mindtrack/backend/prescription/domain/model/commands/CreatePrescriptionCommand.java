package com.mindtrack.backend.prescription.domain.model.commands;

import java.time.LocalDate;

public record CreatePrescriptionCommand(
        LocalDate startDate,
        LocalDate endDate
) {
}
