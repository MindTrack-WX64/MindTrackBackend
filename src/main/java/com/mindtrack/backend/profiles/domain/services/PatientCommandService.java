package com.mindtrack.backend.profiles.domain.services;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;

import java.util.Optional;

public interface PatientCommandService {
    Optional<Patient> handle(CreatePatientCommand command);
}
