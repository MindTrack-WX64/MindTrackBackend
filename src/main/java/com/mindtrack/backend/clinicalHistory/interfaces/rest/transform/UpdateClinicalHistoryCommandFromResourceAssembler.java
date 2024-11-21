package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.UpdateClinicalHistoryCommand;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.UpdateClinicalHistoryResource;

public class UpdateClinicalHistoryCommandFromResourceAssembler {
    public static UpdateClinicalHistoryCommand toCommandFromResource(UpdateClinicalHistoryResource resource, Long id) {
        return new UpdateClinicalHistoryCommand(
                id,
                resource.background(),
                resource.consultationReason()
        );
    }
}
