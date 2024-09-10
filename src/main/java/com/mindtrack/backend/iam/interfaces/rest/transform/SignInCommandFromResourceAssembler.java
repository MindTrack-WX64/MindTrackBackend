package com.mindtrack.backend.iam.interfaces.rest.transform;

import com.mindtrack.backend.iam.domain.model.commands.SignInCommand;
import com.mindtrack.backend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
