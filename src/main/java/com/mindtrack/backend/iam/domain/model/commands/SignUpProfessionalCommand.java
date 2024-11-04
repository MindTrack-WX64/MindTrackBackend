package com.mindtrack.backend.iam.domain.model.commands;

import java.time.LocalDate;

public record SignUpProfessionalCommand(
        String username,
        String password,
        String fullName,
        String email,
        String phone,
        String birthDate,
        String professionalType
) {
}
