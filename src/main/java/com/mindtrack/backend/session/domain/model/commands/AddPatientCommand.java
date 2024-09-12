package com.mindtrack.backend.session.domain.model.commands;

public record AddPatientCommand(
        Long patientId,
        Long professionalId
) {
}
