package com.mindtrack.backend.profiles.application.internal.commandservices;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.profiles.domain.services.PatientCommandService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository patientRepository;

    public PatientCommandServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> handle(CreatePatientCommand command) {
        Patient patient = new Patient(command);

        this.patientRepository.save(patient);

        return Optional.of(patient);
    }
}
