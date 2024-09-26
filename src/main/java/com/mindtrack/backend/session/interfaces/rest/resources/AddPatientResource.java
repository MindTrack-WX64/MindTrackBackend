package com.mindtrack.backend.session.interfaces.rest.resources;

public record AddPatientResource(
        Long patientId,
        Long professionalId
) {
}
