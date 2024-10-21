package com.mindtrack.backend.iam.domain.model.commands;

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
