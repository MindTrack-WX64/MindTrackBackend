package com.mindtrack.backend.profiles.interfaces.rest.transform;

import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfessionalResource;

public class ProfessionalResourceFromEntityAssembler {
    public static ProfessionalResource toResourceFromEntity(Professional professional) {
        return new ProfessionalResource(
                professional.getUserId(),
                professional.getId(),
                professional.getFullName(),
                professional.getEmail(),
                professional.getPhone(),
                professional.getBirthDate(),
                professional.getProfessionalType()
        );
    }
}
