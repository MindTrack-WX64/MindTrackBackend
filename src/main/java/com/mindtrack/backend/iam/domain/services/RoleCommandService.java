package com.mindtrack.backend.iam.domain.services;

import com.mindtrack.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
