package com.mindtrack.backend.session.interfaces.rest.resources;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;
import java.time.LocalDate;
import java.util.List;

public record ProfessionalResource(
        Long professionalId,
        UserResource userResource,
        String professionalType,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        List<PatientResource> patients
) {
}
