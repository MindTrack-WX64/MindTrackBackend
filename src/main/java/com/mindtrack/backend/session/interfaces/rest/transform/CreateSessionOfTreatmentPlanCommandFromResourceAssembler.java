package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.session.domain.model.commands.CreateSessionOfTreatmentPlanCommand;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateSessionResource;

public class CreateSessionOfTreatmentPlanCommandFromResourceAssembler {
    public static CreateSessionOfTreatmentPlanCommand toCommandFromResource(CreateSessionResource resource, Long treatmentPlanId) {
        return new CreateSessionOfTreatmentPlanCommand(
                resource.patientId(),
                resource.professionalId(),
                resource.sessionDate(),
                treatmentPlanId
        );
    }
}
