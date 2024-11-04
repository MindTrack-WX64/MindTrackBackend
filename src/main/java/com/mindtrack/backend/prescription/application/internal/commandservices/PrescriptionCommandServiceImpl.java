package com.mindtrack.backend.prescription.application.internal.commandservices;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.domain.services.PrescriptionCommandService;
import com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrescriptionCommandServiceImpl implements PrescriptionCommandService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionCommandServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Optional<Prescription> handle(CreatePrescriptionCommand command) {
       /* Patient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        Professional professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found")); */

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
