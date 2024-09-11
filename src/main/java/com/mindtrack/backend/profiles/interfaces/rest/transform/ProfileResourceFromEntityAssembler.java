package com.mindtrack.backend.profiles.interfaces.rest.transform;

import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;
import com.mindtrack.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUserId());
        return new ProfileResource(
                entity.getId(),
                userResource,
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBirthDate()
        );
    }
}
