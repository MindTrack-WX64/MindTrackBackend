package com.mindtrack.backend.treatmentPlan.application.internal.commandservices;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories.PrescriptionRepository;
import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.infrastructure.persistence.jpa.repositories.SessionRepository;
import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.commands.*;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import com.mindtrack.backend.treatmentPlan.domain.services.TreatmentPlanCommandService;
import com.mindtrack.backend.treatmentPlan.infrastructure.persistence.jpa.repositories.TreatmentPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentPlanCommandServiceImpl implements TreatmentPlanCommandService {
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final SessionRepository sessionRepository;
    private final PrescriptionRepository prescriptionRepository;

    public TreatmentPlanCommandServiceImpl(TreatmentPlanRepository treatmentPlanRepository, SessionRepository sessionRepository, PrescriptionRepository prescriptionRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.sessionRepository = sessionRepository;
        this.prescriptionRepository = prescriptionRepository;
    }


    @Override
    public Optional<TreatmentPlan> handle(CreateTreatmentPlanCommand command) {
        /*atient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Professional professional = this.professionalRepository.findById(command.professionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found")); */

        TreatmentPlan treatmentPlan = new TreatmentPlan(command);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<TreatmentPlan> handle(AddBiologicalFunctionCommand command) {
        TreatmentPlan treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        treatmentPlan.addBiologicalFunction(command);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<TreatmentPlan> handle(AddDiagnosticCommand command) {
        TreatmentPlan treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        treatmentPlan.addDiagnostic(command);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<TreatmentPlan> handle(AddPatientStateCommand command) {
        TreatmentPlan treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        treatmentPlan.addPatientState(command);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<TreatmentPlan> handle(AddPrescriptionCommand command) {
        TreatmentPlan treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        Prescription prescription = this.prescriptionRepository.findById(command.prescriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found"));

        treatmentPlan.addPrescription(prescription);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<TreatmentPlan> handle(AddSessionCommand command) {
        TreatmentPlan treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        Session session = this.sessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        treatmentPlan.addSession(session);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<TreatmentPlan> handle(AddTaskCommand command) {
        TreatmentPlan treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        treatmentPlan.addTask(command);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);

        return Optional.of(savedTreatmentPlan);
    }

    @Override
    public Optional<Task> handle(ExecuteTaskCommand command) {
        var treatmentPlan = this.treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Treatment Plan not found"));

        treatmentPlan.executeTask(command);

        var savedTreatmentPlan = this.treatmentPlanRepository.save(treatmentPlan);
        return Optional.of(savedTreatmentPlan.getTasks().get(savedTreatmentPlan.getTasks().size() - 1));
    }


}
