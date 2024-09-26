package com.mindtrack.backend.clinicalHistory.domain.model.commands;

public record AddSymptomCommand(
    String symptom,
    Long clinicalHistoryId
) {
}
