package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import com.mindtrack.backend.prescription.interfaces.rest.resources.PrescriptionResource;
import com.mindtrack.backend.session.interfaces.rest.resources.SessionResource;

import java.time.LocalDate;
import java.util.List;

public record TreatmentPlanResource(
        Long treatmentPlanId,
        Long patientId,
        Long professionalId,
        LocalDate startDate,
        LocalDate endDate,
        boolean isFinished,
        String description,
        List<SessionResource> sessions,
        List<PrescriptionResource> prescriptions,
        List<String> patientStates,
        List<String> biologicalFunctions,
        List<String> diagnostics,
        List<String> tasks
) {
}
