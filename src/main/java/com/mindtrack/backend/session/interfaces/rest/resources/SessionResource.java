package com.mindtrack.backend.session.interfaces.rest.resources;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;

import java.time.LocalDate;
import java.util.List;

public record SessionResource(
        Long id,
        PatientResource patient,
        ProfessionalResource professional,
        LocalDate sessionDate,
        List<NoteResource> notes
) {
}
