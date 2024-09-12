package com.mindtrack.backend.prescription.interfaces.rest.resources;

public record AddPillsToDescriptionResource(
        String name,
        String description,
        int quantity,
        Long prescriptionId
) {
}
