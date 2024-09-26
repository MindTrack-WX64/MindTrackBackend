package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record AddPrescriptionCommand(
        Long prescriptionId,
        Long treatmentPlanId
) {
}
