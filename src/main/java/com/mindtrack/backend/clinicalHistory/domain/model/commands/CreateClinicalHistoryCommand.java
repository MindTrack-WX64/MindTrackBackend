package com.mindtrack.backend.clinicalHistory.domain.model.commands;

import java.time.LocalDate;

public record CreateClinicalHistoryCommand(
        Long patientId,
        String background,
        String consultationReason,
        LocalDate consultationDate
) {
}
