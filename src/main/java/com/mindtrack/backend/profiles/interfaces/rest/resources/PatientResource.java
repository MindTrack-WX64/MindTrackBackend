package com.mindtrack.backend.profiles.interfaces.rest.resources;

import java.time.LocalDate;

public record PatientResource(
        Long patientId,
        Long userId,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        boolean clinicalHistoryStatus,
        Long professionalId
) {
}
