package com.mindtrack.backend.profiles.application.internal.queryservices;

import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfessionalByIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfessionalByUserIdQuery;
import com.mindtrack.backend.profiles.domain.services.ProfessionalQueryService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionalQueryServiceImpl implements ProfessionalQueryService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalQueryServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Optional<Professional> handle(GetProfessionalByIdQuery query) {
        return professionalRepository.findById(query.professionalId());
    }

    @Override
    public Optional<Professional> handle(GetProfessionalByUserIdQuery query) {
        return professionalRepository.findByUserId(query.userId());
    }
}
