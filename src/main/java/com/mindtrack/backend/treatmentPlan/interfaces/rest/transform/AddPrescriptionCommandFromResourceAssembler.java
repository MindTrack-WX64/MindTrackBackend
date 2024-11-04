package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddPrescriptionCommand;

public class AddPrescriptionCommandFromResourceAssembler {
    public static AddPrescriptionCommand toCommandFromResource(Long prescriptionId, Long treatmentPlanId) {
        return new AddPrescriptionCommand(
                prescriptionId,
                treatmentPlanId
        );
    }
}
