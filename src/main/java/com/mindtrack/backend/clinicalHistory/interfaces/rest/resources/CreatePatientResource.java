package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

public record CreatePatientResource(
        boolean clinicalHistoryStatus,
        Long profileId
) {
}
