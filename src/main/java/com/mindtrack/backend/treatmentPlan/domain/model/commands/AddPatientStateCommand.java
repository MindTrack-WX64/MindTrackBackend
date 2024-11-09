package com.mindtrack.backend.treatmentPlan.domain.model.commands;

import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.MoodStates;

public record AddPatientStateCommand(
        MoodStates moodState,
        Long treatmentPlanId
) {
}
