package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddBiologicalFunctionCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddBiologicalFunctionResource;

public class AddBiologicalFunctionsCommandFromResourceAssembler {
    public static AddBiologicalFunctionCommand toCommandFromResource(AddBiologicalFunctionResource resource) {
        return new AddBiologicalFunctionCommand(
                resource.date(),
                resource.hunger(),
                resource.sleep(),
                resource.hydration(),
                resource.hunger(),
                resource.treatmentPlanId()
        );
    }
}
