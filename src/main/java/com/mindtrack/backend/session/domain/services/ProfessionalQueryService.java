package com.mindtrack.backend.session.domain.services;

import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.domain.model.queries.GetAllProfessionalQuery;
import com.mindtrack.backend.session.domain.model.queries.GetProfessionalByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfessionalQueryService {
    Optional<Professional> handle(GetProfessionalByIdQuery query);
    List<Professional> handle(GetAllProfessionalQuery query);
}
