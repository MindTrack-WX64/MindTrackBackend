package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.CreatePatientResource;
import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;
import com.mindtrack.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class CreatePatientCommandFromResourceAssembler {
    public static CreatePatientCommand toCommandFromResource(CreatePatientResource resource) {
        return new CreatePatientCommand(
                resource.userId(),
                resource.fullName(),
                resource.email(),
                resource.phone(),
                resource.birthDate()
        );
    }
}
