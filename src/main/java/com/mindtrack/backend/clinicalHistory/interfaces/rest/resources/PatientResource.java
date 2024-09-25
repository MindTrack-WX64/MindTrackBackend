package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;

import java.time.LocalDate;

public record PatientResource(
        Long patientId,
        UserResource user,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        boolean clinicalHistoryStatus
) {
}
