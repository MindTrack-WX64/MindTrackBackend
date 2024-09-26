package com.mindtrack.backend.iam.domain.model.queries;

import com.mindtrack.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery (Roles roleName) {
}
