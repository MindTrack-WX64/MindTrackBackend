package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.interfaces.rest.resources.AddPillsToDescriptionResource;

public class AddPillsToDescriptionCommandFromResourceAssembler {
    public static AddPillsToDescriptionCommand toCommandFromResource(AddPillsToDescriptionResource resource, Long id) {
        return new AddPillsToDescriptionCommand(
                resource.name(),
                resource.description(),
                resource.quantity(),
                resource.frequency(),
                id
        );
    }
}
