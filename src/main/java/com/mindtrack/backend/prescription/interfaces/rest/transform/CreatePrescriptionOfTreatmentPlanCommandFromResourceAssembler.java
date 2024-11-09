package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionOfTreatmentPlanCommand;
import com.mindtrack.backend.prescription.interfaces.rest.resources.CreatePrescriptionResource;

public class CreatePrescriptionOfTreatmentPlanCommandFromResourceAssembler {
    public static CreatePrescriptionOfTreatmentPlanCommand toCommandFromResource(CreatePrescriptionResource resource, Long id) {
        return new CreatePrescriptionOfTreatmentPlanCommand(
                resource.patientId(),
                resource.professionalId(),
                resource.startDate(),
                resource.endDate(),
                id
        );
    }
}
