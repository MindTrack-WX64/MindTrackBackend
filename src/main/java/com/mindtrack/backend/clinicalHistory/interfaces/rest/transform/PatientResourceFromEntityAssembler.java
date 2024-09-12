package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfileResource;
import com.mindtrack.backend.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient entity) {
        ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(entity.getProfile());
        return new PatientResource(entity.getId(), profileResource, entity.isClinicalHistoryStatus());
    }
}
