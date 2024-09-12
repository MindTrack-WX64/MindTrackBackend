package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.AddSymptomCommand;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.AddSymptomResource;

public class AddSymptomCommandFromResourceAssembler {
    public static AddSymptomCommand toCommandFromResource(AddSymptomResource resource) {
        return new AddSymptomCommand(
                resource.symptom(),
                resource.clinicalHistoryId()
        );
    }
}
