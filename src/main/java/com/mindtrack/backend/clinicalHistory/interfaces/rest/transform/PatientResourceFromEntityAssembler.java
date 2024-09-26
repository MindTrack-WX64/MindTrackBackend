package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.iam.interfaces.rest.resources.UserResource;
import com.mindtrack.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient entity) {
        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser());
        return new PatientResource(
                entity.getId(),
                userResource,
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBirthDate(),
                entity.isClinicalHistoryStatus());
    }
}
