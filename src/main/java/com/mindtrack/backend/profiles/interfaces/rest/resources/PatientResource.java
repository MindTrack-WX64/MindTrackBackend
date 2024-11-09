package com.mindtrack.backend.profiles.interfaces.rest.resources;

import java.time.LocalDate;

public record PatientResource(
        Long userId,
        Long patientId,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        boolean clinicalHistoryStatus
) {
}
