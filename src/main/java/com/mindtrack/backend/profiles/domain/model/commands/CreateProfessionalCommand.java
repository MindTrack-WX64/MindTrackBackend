package com.mindtrack.backend.profiles.domain.model.commands;

import java.time.LocalDate;

public record CreateProfessionalCommand(
        String professionalType,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
