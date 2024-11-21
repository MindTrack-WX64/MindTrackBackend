package com.mindtrack.backend.clinicalHistory.domain.services;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.domain.model.commands.AddSymptomCommand;
import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreateClinicalHistoryCommand;
import com.mindtrack.backend.clinicalHistory.domain.model.commands.UpdateClinicalHistoryCommand;

import java.util.Optional;

public interface ClinicalHistoryCommandService {
    Optional<ClinicalHistory> handle(CreateClinicalHistoryCommand command);
    Optional<ClinicalHistory> handle(UpdateClinicalHistoryCommand command);
    Optional<ClinicalHistory> handle(AddSymptomCommand command);
}
