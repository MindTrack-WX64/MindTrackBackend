package com.mindtrack.backend.iam.interfaces.rest.transform;

import com.mindtrack.backend.iam.domain.model.commands.SignUpProfessionalCommand;
import com.mindtrack.backend.iam.interfaces.rest.resources.SignUpProfessionalResource;

public class SignUpProfessionalCommandFromResourceAssembler {
    public static SignUpProfessionalCommand toCommandFromResource(SignUpProfessionalResource resource) {
        return new SignUpProfessionalCommand(
                resource.username(),
                resource.password(),
                resource.fullName(),
                resource.email(),
                resource.phone(),
                resource.birthDate(),
                resource.professionalType()
        );
    }
}
