package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.session.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateProfessionalResource;

public class CreateProfessionalCommandFromResourceAssembler {
    public static CreateProfessionalCommand toCommandFromResource(CreateProfessionalResource resource) {
        return new CreateProfessionalCommand(
                resource.professionalType(),
                resource.profileId()
        );
    }
}
