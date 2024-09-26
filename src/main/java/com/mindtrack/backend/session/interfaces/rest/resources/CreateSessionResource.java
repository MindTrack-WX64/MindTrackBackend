package com.mindtrack.backend.session.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateSessionResource(
        Long patientId,
        Long professionalId,
        LocalDate sessionDate
) {
}
