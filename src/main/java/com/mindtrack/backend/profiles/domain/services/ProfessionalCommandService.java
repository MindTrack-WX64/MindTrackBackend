package com.mindtrack.backend.profiles.domain.services;

import com.mindtrack.backend.profiles.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;

import java.util.Optional;

public interface ProfessionalCommandService {
    Optional<Professional> handle(CreateProfessionalCommand command);
}