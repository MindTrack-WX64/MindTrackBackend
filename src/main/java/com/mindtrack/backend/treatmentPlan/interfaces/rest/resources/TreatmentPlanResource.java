package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PrescriptionResource;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;
import com.mindtrack.backend.session.interfaces.rest.resources.SessionResource;

import java.time.LocalDate;
import java.util.List;

public record TreatmentPlanResource(
        Long treatmentPlanId,
        PatientResource patient,
        ProfessionalResource professional,
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
