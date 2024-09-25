package com.mindtrack.backend.session.application.internal.queryservices;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.queries.GetAllProfessionalSessionsQuery;
import com.mindtrack.backend.session.domain.model.queries.GetAllSessionBySessionDateQuery;
import com.mindtrack.backend.session.domain.model.queries.GetAllSessionQuery;
import com.mindtrack.backend.session.domain.model.queries.GetSessionByIdQuery;
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
    public List<Session> handle(GetAllProfessionalSessionsQuery query) {
        return this.sessionRepository.findAllByProfessionalEmail(query.professionalEmail());
    }

    @Override
    public List<Session> handle(GetAllSessionBySessionDateQuery query) {
        return this.sessionRepository.findAllBySessionDate(query.sessionDate());
    }

    @Override
    public List<Session> handle(GetAllSessionQuery query) {
        return this.sessionRepository.findAll();
    }
}
