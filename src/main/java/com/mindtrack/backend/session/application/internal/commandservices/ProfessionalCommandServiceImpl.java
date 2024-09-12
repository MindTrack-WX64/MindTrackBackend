package com.mindtrack.backend.session.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
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
    private final ProfileRepository profileRepository;
    private final PatientRepository patientRepository;

    public ProfessionalCommandServiceImpl(ProfessionalRepository professionalRepository,
                                          ProfileRepository profileRepository, PatientRepository patientRepository) {
        this.professionalRepository = professionalRepository;
        this.profileRepository = profileRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Professional> handle(CreateProfessionalCommand command) {
        Profile profile = this.profileRepository.findById(command.profileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        Professional professional = new Professional(command, profile);

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
