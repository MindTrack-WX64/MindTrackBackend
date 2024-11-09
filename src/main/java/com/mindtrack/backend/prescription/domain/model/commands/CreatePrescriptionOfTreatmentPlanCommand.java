package com.mindtrack.backend.prescription.domain.model.commands;

import java.time.LocalDate;

public record CreatePrescriptionOfTreatmentPlanCommand(
        Long patientId,
        Long professionalId,
        LocalDate startDate,
        LocalDate endDate,
        Long treatmentPlanId
) {
}
