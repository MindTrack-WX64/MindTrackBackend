package com.mindtrack.backend.session.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.mindtrack.backend.session.domain.model.commands.AddPatientCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.domain.services.ProfessionalCommandService;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionalCommandServiceImpl implements ProfessionalCommandService {
    private final ProfessionalRepository professionalRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public ProfessionalCommandServiceImpl(ProfessionalRepository professionalRepository,
                                          UserRepository userRepository, PatientRepository patientRepository) {
        this.professionalRepository = professionalRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Professional> handle(CreateProfessionalCommand command) {
        User user = this.userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getSerializedRoles().contains("PROFESSIONAL")) {
            throw new IllegalArgumentException("Profile is not a professional");
        }

        Professional professional = new Professional(command, user);

        var savedProfessional = this.professionalRepository.save(professional);
        return Optional.of(savedProfessional);
    }

    @Override
    public Optional<Professional> handle(AddPatientCommand command) {
        Professional professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));

        Patient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        if (!professional.getPatients().contains(patient)) {
            professional.addPatient(patient);
        }

        var savedProfessional = this.professionalRepository.save(professional);
        return Optional.of(savedProfessional);
    }
}
