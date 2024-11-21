package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

public record UpdateClinicalHistoryResource(
        String background,
        String consultationReason
) {
}
