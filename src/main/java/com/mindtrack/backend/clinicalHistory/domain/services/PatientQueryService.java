package com.mindtrack.backend.clinicalHistory.domain.services;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetAllPatientQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetPatientByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PatientQueryService {
    Optional<Patient> handle(GetPatientByIdQuery query);
    List<Patient> handle(GetAllPatientQuery query);
}
