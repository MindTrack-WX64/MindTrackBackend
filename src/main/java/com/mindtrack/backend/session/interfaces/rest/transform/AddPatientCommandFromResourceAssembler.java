package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.session.domain.model.commands.AddPatientCommand;
import com.mindtrack.backend.session.interfaces.rest.resources.AddPatientResource;

public class AddPatientCommandFromResourceAssembler {
    public static AddPatientCommand toCommandFromResource(AddPatientResource resource) {
        return new AddPatientCommand(
                resource.patientId(),
                resource.professionalId()
        );
    }
}

