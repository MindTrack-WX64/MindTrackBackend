package com.mindtrack.backend.iam.interfaces.rest.transform;

import com.mindtrack.backend.iam.domain.model.commands.SignUpPatientCommand;
import com.mindtrack.backend.iam.interfaces.rest.resources.SignUpPatientResource;

public class SignUpPatientCommandFromResourceAssembler {
    public static SignUpPatientCommand toCommandFromResource(SignUpPatientResource resource) {
        return new SignUpPatientCommand(
                resource.username(),
                resource.password(),
                resource.fullName(),
                resource.email(),
                resource.phone(),
                resource.birthDate(),
                resource.professionalId()
        );
    }
}
