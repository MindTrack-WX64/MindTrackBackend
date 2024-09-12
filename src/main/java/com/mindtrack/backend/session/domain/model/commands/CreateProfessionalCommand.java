package com.mindtrack.backend.session.domain.model.commands;

public record CreateProfessionalCommand(
        String professionalType,
        Long profileId
) {
}
