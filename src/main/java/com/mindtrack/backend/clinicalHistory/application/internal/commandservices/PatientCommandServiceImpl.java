package com.mindtrack.backend.clinicalHistory.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.domain.services.PatientCommandService;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientCommandServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Patient> handle(CreatePatientCommand command) {
        User user = this.userRepository.findById(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getSerializedRoles().contains("PATIENT")) {
            throw new IllegalArgumentException("Profile is not a patient");
        }

        Patient patient = new Patient(command, user);

        var patientUpdated = this.patientRepository.save(patient);

        return Optional.of(patientUpdated);
    }
}
