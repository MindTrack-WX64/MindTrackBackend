package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PillResource;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PrescriptionResource;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;
import com.mindtrack.backend.session.interfaces.rest.transform.ProfessionalResourceFromEntityAssembler;

import java.util.List;

public class PrescriptionResourceFromEntityAssembler {
    public static PrescriptionResource toResourceFromEntity(Prescription resource) {
        PatientResource patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(resource.getPatient());
        ProfessionalResource professionalResource = ProfessionalResourceFromEntityAssembler.toResourceFromEntity(resource.getProfessional());
        List<PillResource> pillResources = resource.getPills().stream()
                .map(PillResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new PrescriptionResource(
                resource.getId(),
                patientResource,
                professionalResource,
                resource.getStartDate(),
                resource.getEndDate(),
                pillResources
        );
    }
}
