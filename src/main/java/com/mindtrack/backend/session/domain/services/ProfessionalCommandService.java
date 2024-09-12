package com.mindtrack.backend.session.domain.services;

import com.mindtrack.backend.session.domain.model.commands.AddPatientCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.session.domain.model.entities.Professional;

import java.util.Optional;

public interface ProfessionalCommandService {
    Optional<Professional> handle(CreateProfessionalCommand command);
    Optional<Professional> handle(AddPatientCommand command);
}
