package com.mindtrack.backend.prescription.interfaces.rest.resources;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionResource(
        Long prescriptionId,
        PatientResource patient,
        ProfessionalResource professional,
        LocalDate startDate,
        LocalDate endDate,
        List<PillResource> pills
) {
}
