package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

public record AddSymptomResource(
        String symptom,
        Long clinicalHistoryId
) {
}
