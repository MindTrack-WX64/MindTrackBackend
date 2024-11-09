package com.mindtrack.backend.session.interfaces.rest.resources;

import java.util.List;

public record SessionResource(
        Long id,
        Long patientId,
        Long professionalId,
        String sessionDate,
        Long treatmentPlanId
) {
}
