package com.mindtrack.backend.profiles.interfaces.rest.resources;

import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;

import java.time.LocalDate;

public record ProfileResource(
        Long id,
        UserResource user,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
