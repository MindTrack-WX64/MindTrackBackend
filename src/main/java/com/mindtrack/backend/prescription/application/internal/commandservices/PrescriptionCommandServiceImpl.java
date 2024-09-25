package com.mindtrack.backend.prescription.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.domain.services.PrescriptionCommandService;
import com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories.PrescriptionRepository;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrescriptionCommandServiceImpl implements PrescriptionCommandService {
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    public PrescriptionCommandServiceImpl(PrescriptionRepository prescriptionRepository, PatientRepository patientRepository, ProfessionalRepository professionalRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Optional<Prescription> handle(CreatePrescriptionCommand command) {
        Patient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        Professional professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));

        if (command.startDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The start date must be after today");
        }
        if (command.endDate().isBefore(command.startDate())) {
            throw new IllegalArgumentException("The end date must be after the start date");
        }
        Prescription prescription = new Prescription(command, patient, professional);
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
