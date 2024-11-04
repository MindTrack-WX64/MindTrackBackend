package com.mindtrack.backend.session.application.internal.queryservices;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.entities.Note;
import com.mindtrack.backend.session.domain.model.queries.*;
import com.mindtrack.backend.session.domain.services.SessionQueryService;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionQueryServiceImpl implements SessionQueryService {
    private final SessionRepository sessionRepository;

    public SessionQueryServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> handle(GetSessionByIdQuery query) {
        return this.sessionRepository.findById(query.sessionId());
    }

    @Override
    public List<Session> handle(GetAllSessionsByProfessionalIdQuery query) {
        return this.sessionRepository.findAllByProfessionalId(query.professionalId());
    }

    @Override
    public List<Note> handle(GetAllNotesBySessionIdQuery query) {
        var session = this.sessionRepository.findById(query.sessionId());
        return session.map(Session::getNotes).orElse(null);
    }
}
