package com.mindtrack.backend.treatmentPlan.domain.model.commands;

public record AddTaskCommand(
        String title,
        String description
) {
}
