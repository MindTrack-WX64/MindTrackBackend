package com.mindtrack.backend.profiles.domain.services;

import com.mindtrack.backend.profiles.domain.model.aggregates.Professional;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfessionalByIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfessionalByUserIdQuery;

import java.util.Optional;

public interface ProfessionalQueryService {
    Optional<Professional> handle(GetProfessionalByIdQuery query);
    Optional<Professional> handle(GetProfessionalByUserIdQuery query);
}
