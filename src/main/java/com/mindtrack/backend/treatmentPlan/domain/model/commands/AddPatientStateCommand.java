package com.mindtrack.backend.treatmentPlan.domain.model.commands;

import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.MoodStates;

import java.time.LocalDate;

public record AddPatientStateCommand(
        LocalDate date,
        MoodStates moodState,
        Long treatmentPlanId
) {
}
