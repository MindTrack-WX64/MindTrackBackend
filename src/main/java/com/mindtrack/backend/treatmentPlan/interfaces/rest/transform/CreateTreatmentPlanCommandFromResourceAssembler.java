package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.CreateTreatmentPlanCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.CreateTreatmentPlanResource;

public class CreateTreatmentPlanCommandFromResourceAssembler {
    public static CreateTreatmentPlanCommand toCommandFromResource(CreateTreatmentPlanResource resource) {
        return new CreateTreatmentPlanCommand(
                resource.patientId(),
                resource.professionalId(),
                resource.description()
        );
    }
}
