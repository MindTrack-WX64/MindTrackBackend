package com.mindtrack.backend.prescription.domain.model.commands;

public record CreatePrescriptionCommand(
        Long patientId,
        Long professionalId,
        String startDate,
        String endDate
) {
}
