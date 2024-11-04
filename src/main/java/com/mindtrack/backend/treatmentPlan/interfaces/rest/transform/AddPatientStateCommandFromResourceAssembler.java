package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddPatientStateCommand;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.MoodStates;

public class AddPatientStateCommandFromResourceAssembler {
    public static AddPatientStateCommand toCommandFromResource(String moodState, Long id) {
        MoodStates moodStates = MoodStates.valueOf(moodState);
        return new AddPatientStateCommand(
                moodStates,
                id
        );
    }
}
