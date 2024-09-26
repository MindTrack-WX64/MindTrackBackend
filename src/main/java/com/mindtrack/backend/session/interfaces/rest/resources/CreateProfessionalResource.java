package com.mindtrack.backend.session.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateProfessionalResource(
        Long userId,
        String professionalType,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
