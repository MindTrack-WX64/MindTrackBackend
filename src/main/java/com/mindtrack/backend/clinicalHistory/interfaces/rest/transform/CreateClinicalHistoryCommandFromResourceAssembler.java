package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreateClinicalHistoryCommand;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.CreateClinicalHistoryResource;

public class CreateClinicalHistoryCommandFromResourceAssembler {
    public static CreateClinicalHistoryCommand toCommandFromResource(CreateClinicalHistoryResource resource) {
        return new CreateClinicalHistoryCommand(
                resource.patientId(),
                resource.background(),
                resource.consultationReason(),
                resource.consultationDate()
        );
    }
}
