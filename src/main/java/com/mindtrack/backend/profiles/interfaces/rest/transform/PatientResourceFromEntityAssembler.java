package com.mindtrack.backend.profiles.interfaces.rest.transform;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.interfaces.rest.resources.PatientResource;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient patient) {
        return new PatientResource(
                patient.getId(),
                patient.getUserId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getBirthDate(),
                patient.isClinicalHistoryStatus()
        );
    }
}
