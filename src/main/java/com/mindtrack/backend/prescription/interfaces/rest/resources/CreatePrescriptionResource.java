package com.mindtrack.backend.prescription.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record CreatePrescriptionResource(
        String frequency,
        LocalDate startDate,
        LocalDate endDate
) {
}
