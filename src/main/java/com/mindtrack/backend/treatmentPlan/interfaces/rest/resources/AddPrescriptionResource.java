package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

public record AddPrescriptionResource(
        Long prescriptionId,
        Long treatmentPlanId
) {
}
