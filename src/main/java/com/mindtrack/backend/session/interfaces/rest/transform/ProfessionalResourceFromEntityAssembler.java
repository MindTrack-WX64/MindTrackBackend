package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;
import com.mindtrack.backend.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;

import java.util.List;

public class ProfessionalResourceFromEntityAssembler {
    public static ProfessionalResource toResourceFromEntity(Professional entity) {
        ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(entity.getProfile());
        List<PatientResource> patientResources = entity.getPatients().stream().map(PatientResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ProfessionalResource(
                entity.getId(),
                profileResource,
                entity.getProfessionalType(),
                patientResources
        );
    }
}
