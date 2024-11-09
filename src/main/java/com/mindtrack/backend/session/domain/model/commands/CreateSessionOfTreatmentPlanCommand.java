package com.mindtrack.backend.session.domain.model.commands;

import java.time.LocalDate;

public record CreateSessionOfTreatmentPlanCommand(
        Long patientId,
        Long professionalId,
        LocalDate sessionDate,
        Long treatmentPlanId
) {
}
