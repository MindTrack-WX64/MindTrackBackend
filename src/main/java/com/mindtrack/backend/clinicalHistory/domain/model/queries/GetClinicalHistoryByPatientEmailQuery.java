package com.mindtrack.backend.clinicalHistory.domain.model.queries;

public record GetClinicalHistoryByPatientEmailQuery(
    String patientEmail
) {
}
