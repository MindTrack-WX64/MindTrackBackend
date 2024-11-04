package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddBiologicalFunctionCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddBiologicalFunctionResource;

public class AddBiologicalFunctionsCommandFromResourceAssembler {
    public static AddBiologicalFunctionCommand toCommandFromResource(AddBiologicalFunctionResource resource, Long id) {
        return new AddBiologicalFunctionCommand(
                resource.hunger(),
                resource.sleep(),
                resource.hydration(),
                resource.hunger(),
                id
        );
    }
}
