package com.mindtrack.backend.clinicalHistory.domain.services;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;

import java.util.Optional;

public interface PatientCommandService {
    Optional<Patient> handle(CreatePatientCommand command);
}
