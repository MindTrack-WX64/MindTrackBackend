package com.mindtrack.backend.session.interfaces.rest.resources;

public record CreateProfessionalResource(
        String professionalType,
        Long profileId
) {
}
