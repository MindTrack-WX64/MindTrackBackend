package com.mindtrack.backend.iam.domain.model.commands;

import java.time.LocalDate;

public record SignUpPatientCommand(
        String username,
        String password,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        boolean clinicalHistoryStatus
) {
}
