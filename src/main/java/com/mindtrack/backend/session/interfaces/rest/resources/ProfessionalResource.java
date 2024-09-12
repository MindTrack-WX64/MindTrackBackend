package com.mindtrack.backend.session.interfaces.rest.resources;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;

import java.util.List;

public record ProfessionalResource(
        Long id,
        ProfileResource profile,
        String professionalType,
        List<PatientResource> patients
) {
}
