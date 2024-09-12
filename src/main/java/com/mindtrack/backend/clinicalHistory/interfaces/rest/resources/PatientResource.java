package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;

public record PatientResource(
        Long patientId,
        ProfileResource profile,
        boolean clinicalHistoryStatus
) {
}
