package com.mindtrack.backend.prescription.domain.services;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.domain.model.queries.GetAllPrescriptionByPillNameQuery;
import com.mindtrack.backend.prescription.domain.model.queries.GetAllPrescriptionQuery;
import com.mindtrack.backend.prescription.domain.model.queries.GetPrescriptionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PrescriptionQueryService {
    Optional<Prescription> handle(GetPrescriptionByIdQuery query);
    List<Prescription> handle(GetAllPrescriptionQuery query);
    List<Prescription> handle(GetAllPrescriptionByPillNameQuery query);
}
