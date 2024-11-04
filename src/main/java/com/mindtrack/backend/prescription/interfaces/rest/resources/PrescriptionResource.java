package com.mindtrack.backend.prescription.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionResource(
        Long prescriptionId,
        Long patientId,
        Long professionalId,
        String startDate,
        String endDate,
        List<PillResource> pills
) {
}
