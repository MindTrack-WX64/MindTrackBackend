package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.StartTaskCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.StartTaskResource;

public class StartTaskCommandFromResourceAssembler {
    public static StartTaskCommand toCommandFromResource(StartTaskResource resource) {
        return new StartTaskCommand(
                resource.taskId(),
                resource.treatmentPlanId()
        );
    }
}
