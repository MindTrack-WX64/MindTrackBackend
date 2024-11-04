package com.mindtrack.backend.profiles.interfaces.rest.resources;

import java.time.LocalDate;

public record ProfessionalResource(
        Long userId,
        Long professionalId,
        String fullName,
        String email,
        String phone,
        String birthDate,
        String professionalType
) {
}
