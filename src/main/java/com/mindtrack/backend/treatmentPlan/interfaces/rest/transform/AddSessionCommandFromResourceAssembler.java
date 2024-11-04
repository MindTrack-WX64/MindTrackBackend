package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddSessionCommand;

public class AddSessionCommandFromResourceAssembler {
    public static AddSessionCommand toCommandFromResource(Long sessionId, Long treatmentPlanId) {
        return new AddSessionCommand(
                sessionId,
                treatmentPlanId
        );
    }
}
