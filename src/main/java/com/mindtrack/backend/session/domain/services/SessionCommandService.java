package com.mindtrack.backend.session.domain.services;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionOfTreatmentPlanCommand;

import java.util.Optional;

public interface SessionCommandService {
    Optional<Session> handle(CreateSessionCommand command);
    Optional<Session> handle(CreateSessionOfTreatmentPlanCommand command);
    Optional<Session> handle(CreateNoteCommand command);
}
