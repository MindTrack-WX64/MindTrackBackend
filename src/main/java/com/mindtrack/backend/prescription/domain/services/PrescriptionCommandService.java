package com.mindtrack.backend.prescription.domain.services;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;

import java.util.Optional;

public interface PrescriptionCommandService {
    Optional<Prescription> handle(CreatePrescriptionCommand command);
    Optional<Prescription> handle(AddPillsToDescriptionCommand command);
}