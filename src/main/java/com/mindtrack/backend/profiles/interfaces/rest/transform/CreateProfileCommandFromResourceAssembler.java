package com.mindtrack.backend.profiles.interfaces.rest.transform;

import com.mindtrack.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.mindtrack.backend.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.userId(),
                resource.fullName(),
                resource.email(),
                resource.phone(),
                resource.birthDate()
        );
    }
}
