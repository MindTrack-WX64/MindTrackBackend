package com.mindtrack.backend.session.interfaces.rest.resources;

public record CreateSessionResource(
        Long patientId,
        Long professionalId,
        String sessionDate
) {
}
