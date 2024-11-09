package com.mindtrack.backend.prescription.interfaces.rest.resources;

public record CreatePrescriptionResource(
        Long patientId,
        Long professionalId,
        String startDate,
        String endDate
) {
}
