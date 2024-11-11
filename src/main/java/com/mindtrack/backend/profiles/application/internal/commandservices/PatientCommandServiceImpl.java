package com.mindtrack.backend.profiles.application.internal.commandservices;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;
import com.mindtrack.backend.profiles.domain.model.commands.AddPatientToProfessionalCommand;
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
        Patient patient = new Patient(command);

        this.patientRepository.save(patient);

        return Optional.of(patient);
    }

    @Override
    public Optional<Patient> handle(AddPatientToProfessionalCommand command) {
        var patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        var professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));

        patient.setProfessional(professional);

        this.patientRepository.save(patient);

        return Optional.of(patient);
    }
}
