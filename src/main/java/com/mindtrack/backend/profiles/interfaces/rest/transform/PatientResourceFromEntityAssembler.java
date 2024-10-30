package com.mindtrack.backend.profiles.interfaces.rest.transform;

import com.mindtrack.backend.profiles.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient patient) {
        return new PatientResource(
                patient.getUserId(),
                patient.getId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getBirthDate(),
                patient.isClinicalHistoryStatus()
        );
    }
}
