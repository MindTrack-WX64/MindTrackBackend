package com.mindtrack.backend.session.application.internal.queryservices;

import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.domain.model.queries.GetAllProfessionalQuery;
import com.mindtrack.backend.session.domain.model.queries.GetProfessionalByIdQuery;
import com.mindtrack.backend.session.domain.services.ProfessionalQueryService;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalQueryServiceImpl implements ProfessionalQueryService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalQueryServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Optional<Professional> handle(GetProfessionalByIdQuery query) {
        return this.professionalRepository.findById(query.professionalId());
    }

    @Override
    public List<Professional> handle(GetAllProfessionalQuery query) {
        return this.professionalRepository.findAll();
    }
}
