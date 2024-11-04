package com.mindtrack.backend.prescription.application.internal.queryservices;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.domain.model.queries.GetAllPrescriptionQuery;
import com.mindtrack.backend.prescription.domain.model.queries.GetPrescriptionByIdQuery;
import com.mindtrack.backend.prescription.domain.services.PrescriptionQueryService;
import com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionQueryServiceImpl implements PrescriptionQueryService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionQueryServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Optional<Prescription> handle(GetPrescriptionByIdQuery query) {
        return this.prescriptionRepository.findById(query.prescriptionId());
    }

    @Override
    public List<Prescription> handle(GetAllPrescriptionQuery query) {
        return this.prescriptionRepository.findAll();
    }

}
