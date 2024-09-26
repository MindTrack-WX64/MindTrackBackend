package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddPatientStateCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddPatientStateResource;

public class AddPatientStateCommandFromResourceAssembler {
    public static AddPatientStateCommand toCommandFromResource(AddPatientStateResource resource) {
        return new AddPatientStateCommand(
                resource.date(),
                resource.moodState(),
                resource.treatmentPlanId()
        );
    }
}
