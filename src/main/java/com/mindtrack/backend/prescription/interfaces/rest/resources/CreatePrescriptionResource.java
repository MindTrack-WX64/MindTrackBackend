package com.mindtrack.backend.prescription.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record CreatePrescriptionResource(
        LocalDate startDate,
        LocalDate endDate
) {
}
