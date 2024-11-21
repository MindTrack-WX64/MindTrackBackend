package com.mindtrack.backend.clinicalHistory.domain.model.commands;

public record UpdateClinicalHistoryCommand(
        Long clinicalHistoryId,
        String background,
        String consultationReason
) {
}
