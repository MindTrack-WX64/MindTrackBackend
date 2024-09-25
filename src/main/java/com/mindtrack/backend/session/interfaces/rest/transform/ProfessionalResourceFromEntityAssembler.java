package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;
import com.mindtrack.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;

import java.util.List;

public class ProfessionalResourceFromEntityAssembler {
    public static ProfessionalResource toResourceFromEntity(Professional entity) {
        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser());
        List<PatientResource> patientResources = entity.getPatients().stream().map(PatientResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ProfessionalResource(
                entity.getId(),
                userResource,
                entity.getProfessionalType(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBirthDate(),
                patientResources
        );
    }
}
