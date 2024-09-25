package com.mindtrack.backend.clinicalHistory.domain.model.commands;

import java.time.LocalDate;

public record CreatePatientCommand(
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
