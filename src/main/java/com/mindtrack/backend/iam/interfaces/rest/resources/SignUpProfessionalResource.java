package com.mindtrack.backend.iam.interfaces.rest.resources;

import java.time.LocalDate;

public record SignUpProfessionalResource(
        String username,
        String password,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        String professionalType
) {
}
