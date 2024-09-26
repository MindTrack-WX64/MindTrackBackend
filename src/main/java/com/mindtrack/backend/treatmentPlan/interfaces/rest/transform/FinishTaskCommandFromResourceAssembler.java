package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.FinishTaskCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.FinishTaskResource;

public class FinishTaskCommandFromResourceAssembler {
    public static FinishTaskCommand toCommandFromResource(FinishTaskResource resource) {
        return new FinishTaskCommand(
                resource.taskId(),
                resource.treatmentPlanId()
        );
    }
}
