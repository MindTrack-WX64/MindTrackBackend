package com.mindtrack.backend.prescription.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionResource(
        Long prescriptionId,
        String frequency,
        LocalDate startDate,
        LocalDate endDate,
        List<PillResource> pills
) {
}
