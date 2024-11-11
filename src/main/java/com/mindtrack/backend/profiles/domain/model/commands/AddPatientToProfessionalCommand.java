package com.mindtrack.backend.profiles.domain.model.commands;

public record AddPatientToProfessionalCommand(
        Long patientId,
        Long professionalId
) {
}
