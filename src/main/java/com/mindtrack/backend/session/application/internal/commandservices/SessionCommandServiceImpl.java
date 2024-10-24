package com.mindtrack.backend.session.application.internal.commandservices;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.domain.services.SessionCommandService;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionCommandServiceImpl implements SessionCommandService {
    private final SessionRepository sessionRepository;

    public SessionCommandServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> handle(CreateSessionCommand command) {
        /*Patient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Professional professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found")); */

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
