package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;

import java.time.LocalDate;

public record PatientResource(
        Long patientId,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        boolean clinicalHistoryStatus
) {
}
