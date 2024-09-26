package com.mindtrack.backend.treatmentPlan.interfaces.rest.resources;

import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.MoodStates;

import java.time.LocalDate;

public record AddPatientStateResource(
        LocalDate date,
        MoodStates moodState,
        Long treatmentPlanId
) {
}
