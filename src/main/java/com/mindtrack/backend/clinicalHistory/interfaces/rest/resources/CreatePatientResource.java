package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

import java.time.LocalDate;

public record CreatePatientResource(
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
