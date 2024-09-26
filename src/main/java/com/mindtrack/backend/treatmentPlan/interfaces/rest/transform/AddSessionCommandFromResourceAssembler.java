package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddSessionCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddSessionResource;

public class AddSessionCommandFromResourceAssembler {
    public static AddSessionCommand toCommandFromResource(AddSessionResource resource) {
        return new AddSessionCommand(
                resource.sessionId(),
                resource.treatmentPlanId()
        );
    }
}
