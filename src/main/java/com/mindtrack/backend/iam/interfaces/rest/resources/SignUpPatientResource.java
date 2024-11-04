package com.mindtrack.backend.iam.interfaces.rest.resources;

import java.time.LocalDate;

public record SignUpPatientResource(
        String username,
        String password,
        String fullName,
        String email,
        String phone,
        String birthDate
) {
}
