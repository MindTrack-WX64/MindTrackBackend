package com.mindtrack.backend.clinicalHistory.domain.model.commands;

public record CreatePatientCommand(
        boolean clinicalHistoryStatus,
        Long profileId
) {
}
