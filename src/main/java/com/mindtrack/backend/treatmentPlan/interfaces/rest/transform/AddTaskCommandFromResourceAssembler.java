package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddTaskCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddTaskResource;

public class AddTaskCommandFromResourceAssembler {
    public static AddTaskCommand toCommandFromResource(AddTaskResource resource) {
        return new AddTaskCommand(
                resource.title(),
                resource.description(),
                resource.treatmentPlanId()
        );
    }
}
