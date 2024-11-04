package com.mindtrack.backend.session.domain.model.commands;

public record CreateSessionCommand(
        Long patientId,
        Long professionalId,
        String sessionDate
) {
}
