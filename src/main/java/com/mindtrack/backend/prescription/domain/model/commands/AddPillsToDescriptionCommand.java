package com.mindtrack.backend.prescription.domain.model.commands;

public record AddPillsToDescriptionCommand(
        String name,
        String description,
        int quantity,
        String frequency,
        Long prescriptionId
) {
}
