package com.mindtrack.backend.clinicalHistory.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record ClinicalHistoryResource(
        Long clinicalHistoryId,
        PatientResource patient,
        String background,
        String consultationReason,
        LocalDate consultationDate,
        List<String> symptoms
) {
}