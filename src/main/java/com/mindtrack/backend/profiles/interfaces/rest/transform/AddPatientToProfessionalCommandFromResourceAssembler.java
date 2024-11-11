package com.mindtrack.backend.profiles.interfaces.rest.transform;

import com.mindtrack.backend.profiles.domain.model.commands.AddPatientToProfessionalCommand;

public class AddPatientToProfessionalCommandFromResourceAssembler {
    public static AddPatientToProfessionalCommand toCommandFromResource(Long patientId, Long professionalId) {
        return new AddPatientToProfessionalCommand(patientId, professionalId);
    }
}
