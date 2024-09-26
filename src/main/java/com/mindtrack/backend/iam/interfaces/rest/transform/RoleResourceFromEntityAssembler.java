package com.mindtrack.backend.iam.interfaces.rest.transform;

import com.mindtrack.backend.iam.domain.model.entities.Role;
import com.mindtrack.backend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
