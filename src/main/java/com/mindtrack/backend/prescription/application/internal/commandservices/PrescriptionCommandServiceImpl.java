package com.mindtrack.backend.prescription.application.internal.commandservices;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.domain.services.PrescriptionCommandService;
import com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrescriptionCommandServiceImpl implements PrescriptionCommandService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionCommandServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Optional<Prescription> handle(CreatePrescriptionCommand command) {
        if (command.endDate().isBefore(command.startDate())) {
            throw new IllegalArgumentException("The end date must be after the start date");
        }
        Prescription prescription = new Prescription(command);
        var savedPrescription = prescriptionRepository.save(prescription);
        return Optional.of(savedPrescription);
    }

    @Override
    public Optional<Prescription> handle(AddPillsToDescriptionCommand command) {
        Prescription prescription = this.prescriptionRepository.findById(command.prescriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found"));
        prescription.addPill(command);
        var savedPrescription = prescriptionRepository.save(prescription);
        return Optional.of(savedPrescription);
    }
}
