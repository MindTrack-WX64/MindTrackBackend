package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddDiagnosticCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddDiagnosticResource;

public class AddDiagnosticCommandFromResourceAssembler {
    public AddDiagnosticCommand toCommand(AddDiagnosticResource resource) {
        return new AddDiagnosticCommand(
                resource.name(),
                resource.description(),
                resource.date(),
                resource.treatmentPlanId()
        );
    }
}
