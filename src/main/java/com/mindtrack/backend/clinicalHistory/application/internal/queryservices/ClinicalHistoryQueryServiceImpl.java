package com.mindtrack.backend.clinicalHistory.application.internal.queryservices;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetAllClinicalHistoryQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetClinicalHistoryByIdQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetClinicalHistoryByPatientEmailQuery;
import com.mindtrack.backend.clinicalHistory.domain.services.ClinicalHistoryQueryService;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.ClinicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicalHistoryQueryServiceImpl implements ClinicalHistoryQueryService {
    private final ClinicalHistoryRepository clinicalHistoryRepository;

    public ClinicalHistoryQueryServiceImpl(ClinicalHistoryRepository clinicalHistoryRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
    }

    @Override
    public Optional<ClinicalHistory> handle(GetClinicalHistoryByIdQuery query) {
        return this.clinicalHistoryRepository.findById(query.clinicalHistoryId());
    }

    @Override
    public Optional<ClinicalHistory> handle(GetClinicalHistoryByPatientEmailQuery query) {
        return this.clinicalHistoryRepository.findByPatientProfileEmail(query.patientEmail());
    }

    @Override
    public List<ClinicalHistory> handle(GetAllClinicalHistoryQuery query) {
        return this.clinicalHistoryRepository.findAll();
    }
}
