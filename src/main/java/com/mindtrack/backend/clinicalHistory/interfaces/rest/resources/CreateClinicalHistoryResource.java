package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateClinicalHistoryResource(
        Long patientId,
        String background,
        String consultationReason,
        LocalDate consultationDate
) {
}
