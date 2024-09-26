package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddPrescriptionCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddPrescriptionResource;

public class AddPrescriptionCommandFromResourceAssembler {
    public static AddPrescriptionCommand toCommandFromResource(AddPrescriptionResource resource) {
        return new AddPrescriptionCommand(
                resource.prescriptionId(),
                resource.treatmentPlanId()
        );
    }
}
