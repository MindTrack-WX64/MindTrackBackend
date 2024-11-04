package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddDiagnosticCommand;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.AddDiagnosticResource;

public class AddDiagnosticCommandFromResourceAssembler {
    public static AddDiagnosticCommand toCommandFromResource(AddDiagnosticResource resource, Long id) {
        return new AddDiagnosticCommand(
                resource.name(),
                resource.description(),
                id
        );
    }
}
