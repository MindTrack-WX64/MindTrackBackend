package com.mindtrack.backend.clinicalHistory.application.internal.queryservices;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetAllPatientQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetPatientByIdQuery;
import com.mindtrack.backend.clinicalHistory.domain.services.PatientQueryService;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {
    private PatientRepository patientRepository;

    public PatientQueryServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return this.patientRepository.findById(query.patientId());
    }

    @Override
    public List<Patient> handle(GetAllPatientQuery query) {
        return this.patientRepository.findAll();
    }
}
