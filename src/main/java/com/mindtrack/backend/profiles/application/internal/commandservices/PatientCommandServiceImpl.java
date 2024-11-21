package com.mindtrack.backend.profiles.application.internal.commandservices;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.profiles.domain.services.PatientCommandService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    public PatientCommandServiceImpl(PatientRepository patientRepository, ProfessionalRepository professionalRepository) {
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Optional<Patient> handle(CreatePatientCommand command) {
        var professional = this.professionalRepository.findByUserId(command.professionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));

        Patient patient = new Patient(command, professional.getUserId());

        this.patientRepository.save(patient);

        return Optional.of(patient);
    }
}
