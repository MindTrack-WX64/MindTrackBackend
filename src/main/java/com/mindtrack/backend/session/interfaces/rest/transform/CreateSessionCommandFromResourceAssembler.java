package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateSessionResource;

public class CreateSessionCommandFromResourceAssembler {
    public static CreateSessionCommand toCommandFromResource(CreateSessionResource resource) {
        return new CreateSessionCommand(
                resource.patientId(),
                resource.professionalId(),
                resource.sessionDate()
        );
    }
}
