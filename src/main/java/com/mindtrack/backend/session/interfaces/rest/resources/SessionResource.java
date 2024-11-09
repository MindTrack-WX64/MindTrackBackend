package com.mindtrack.backend.session.interfaces.rest.resources;

import java.time.LocalDate;

public record SessionResource(
        Long id,
        Long patientId,
        Long professionalId,
        LocalDate sessionDate,
        Long treatmentPlanId
) {
}
