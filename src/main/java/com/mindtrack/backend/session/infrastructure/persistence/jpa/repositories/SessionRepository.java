package com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByProfessionalId(Long professionalId);
}
