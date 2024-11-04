package com.mindtrack.backend.profiles.domain.services;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.domain.model.queries.GetPatientByIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetPatientByUserIdQuery;

import java.util.Optional;

public interface PatientQueryService {
    Optional<Patient> handle(GetPatientByIdQuery query);
    Optional<Patient> handle(GetPatientByUserIdQuery query);
}
