package com.mindtrack.backend.profiles.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateProfileResource(
        Long userId,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate
) {
}
