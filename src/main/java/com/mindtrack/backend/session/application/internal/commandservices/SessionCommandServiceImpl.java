package com.mindtrack.backend.session.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.domain.services.SessionCommandService;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionCommandServiceImpl implements SessionCommandService {
    private final SessionRepository sessionRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    public SessionCommandServiceImpl(SessionRepository sessionRepository, PatientRepository patientRepository, ProfessionalRepository professionalRepository) {
        this.sessionRepository = sessionRepository;
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Optional<Session> handle(CreateSessionCommand command) {
        Patient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Professional professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Session session = new Session(command , patient, professional);

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
