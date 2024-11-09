package com.mindtrack.backend.session.domain.model.commands;

import java.time.LocalDate;

public record CreateSessionCommand(
        Long patientId,
        Long professionalId,
        LocalDate sessionDate
) {
}
