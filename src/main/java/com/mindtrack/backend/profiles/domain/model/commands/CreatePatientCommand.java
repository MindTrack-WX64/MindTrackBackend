package com.mindtrack.backend.profiles.domain.model.commands;

import java.time.LocalDate;

public record CreatePatientCommand(
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
