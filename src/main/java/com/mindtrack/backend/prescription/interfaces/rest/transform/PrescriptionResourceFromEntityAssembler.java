package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PillResource;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PrescriptionResource;

import java.util.List;

public class PrescriptionResourceFromEntityAssembler {
    public static PrescriptionResource toResourceFromEntity(Prescription resource) {
        List<PillResource> pillResources = resource.getPills().stream()
                .map(PillResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new PrescriptionResource(
                resource.getId(),
                resource.getPatientId(),
                resource.getProfessionalId(),
                resource.getStartDate(),
                resource.getEndDate(),
                pillResources
        );
    }
}
