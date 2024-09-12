package com.mindtrack.backend.clinicalHistory.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.domain.services.PatientCommandService;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository patientRepository;
    private final ProfileRepository profileRepository;

    public PatientCommandServiceImpl(PatientRepository patientRepository, ProfileRepository profileRepository) {
        this.patientRepository = patientRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Patient> handle(CreatePatientCommand command) {
        Profile profile = this.profileRepository.findById(command.profileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        Patient patient = new Patient(command, profile);

        var patientUpdated = this.patientRepository.save(patient);

        return Optional.of(patientUpdated);
    }
}
