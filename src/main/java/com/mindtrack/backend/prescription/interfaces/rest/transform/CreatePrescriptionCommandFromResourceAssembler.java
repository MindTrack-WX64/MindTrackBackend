package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.interfaces.rest.resources.CreatePrescriptionResource;

public class CreatePrescriptionCommandFromResourceAssembler {
    public static CreatePrescriptionCommand toCommandFromResource(CreatePrescriptionResource resource) {
        return new CreatePrescriptionCommand(
                resource.frequency(),
                resource.startDate(),
                resource.endDate()
        );
    }
}
