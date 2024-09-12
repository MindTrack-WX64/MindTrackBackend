package com.mindtrack.backend.clinicalHistory.domain.services;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetAllClinicalHistoryQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetClinicalHistoryByIdQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetClinicalHistoryByPatientEmailQuery;

import java.util.List;
import java.util.Optional;

public interface ClinicalHistoryQueryService {
    Optional<ClinicalHistory> handle(GetClinicalHistoryByIdQuery query);
    Optional<ClinicalHistory> handle(GetClinicalHistoryByPatientEmailQuery query);
    List<ClinicalHistory> handle(GetAllClinicalHistoryQuery query);
}
