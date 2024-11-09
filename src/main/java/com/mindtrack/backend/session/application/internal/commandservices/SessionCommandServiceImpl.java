package com.mindtrack.backend.session.application.internal.commandservices;

import com.mindtrack.backend.session.application.internal.outboundservices.acl.ExternalTreatmentPlanService;
import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionOfTreatmentPlanCommand;
import com.mindtrack.backend.session.domain.services.SessionCommandService;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionCommandServiceImpl implements SessionCommandService {
    private final SessionRepository sessionRepository;
    private final ExternalTreatmentPlanService externalTreatmentPlanService;

    public SessionCommandServiceImpl(SessionRepository sessionRepository, ExternalTreatmentPlanService externalTreatmentPlanService) {
        this.sessionRepository = sessionRepository;
        this.externalTreatmentPlanService = externalTreatmentPlanService;
    }

    @Override
    public Optional<Session> handle(CreateSessionCommand command) {

        Session session = new Session(command);

        var sessionSaved = this.sessionRepository.save(session);
        return Optional.of(sessionSaved);
    }

    @Override
    public Optional<Session> handle(CreateSessionOfTreatmentPlanCommand command) {

        if (command.treatmentPlanId() <= 0) {
            throw new RuntimeException("Invalid treatment plan id");
        }

        if (!this.externalTreatmentPlanService.verifyTreatmentPlanExists(command.treatmentPlanId())) {
            throw new RuntimeException("Treatment plan not found");
        }

        Session session = new Session(command);

        var sessionSaved = this.sessionRepository.save(session);
        return Optional.of(sessionSaved);
    }

    @Override
    public Optional<Session> handle(CreateNoteCommand command) {
        Session session = this.sessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.addNote(command);

        var sessionSaved = this.sessionRepository.save(session);
        return Optional.of(sessionSaved);
    }
}
