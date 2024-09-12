package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.interfaces.rest.resources.AddPillsToDescriptionResource;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PillResource;

import java.util.List;

public class AddPillsToDescriptionCommandFromResourceAssembler {
    public static AddPillsToDescriptionCommand toCommandFromResource(AddPillsToDescriptionResource resource) {
        return new AddPillsToDescriptionCommand(
                resource.name(),
                resource.description(),
                resource.quantity(),
                resource.prescriptionId()
        );
    }
}
