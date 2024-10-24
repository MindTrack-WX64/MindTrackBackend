package com.mindtrack.backend.session.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record SessionResource(
        Long id,
        Long patientId,
        Long professionalId,
        LocalDate sessionDate,
        List<NoteResource> notes
) {
}
