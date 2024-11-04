package com.mindtrack.backend.profiles.application.internal.commandservices;

import com.mindtrack.backend.profiles.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.profiles.domain.services.ProfessionalCommandService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionalCommandServiceImpl implements ProfessionalCommandService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalCommandServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Optional<Professional> handle(CreateProfessionalCommand command) {
        Professional professional = new Professional(command);

        this.professionalRepository.save(professional);
        return Optional.of(professional);
    }
}
