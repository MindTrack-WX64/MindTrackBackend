package com.mindtrack.backend.profiles.application.internal.queryservices;

import com.mindtrack.backend.profiles.domain.model.aggregates.Patient;
import com.mindtrack.backend.profiles.domain.model.queries.GetPatientByIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetPatientByUserIdQuery;
import com.mindtrack.backend.profiles.domain.services.PatientQueryService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {
    private final PatientRepository patientRepository;

    public PatientQueryServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return patientRepository.findById(query.patientId());
    }

    @Override
    public Optional<Patient> handle(GetPatientByUserIdQuery query) {
        return patientRepository.findByUserId(query.userId());
    }
}
