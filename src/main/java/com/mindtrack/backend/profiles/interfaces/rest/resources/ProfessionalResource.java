package com.mindtrack.backend.profiles.interfaces.rest.resources;

import java.time.LocalDate;

public record ProfessionalResource(
        Long professionalId,
        Long userId,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        String professionalType
) {
}
