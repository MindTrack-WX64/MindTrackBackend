package com.mindtrack.backend.treatmentPlan.application.internal.commandservices;

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

    public TreatmentPlanCommandServiceImpl(TreatmentPlanRepository treatmentPlanRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }


    @Override
    public Optional<TreatmentPlan> handle(CreateTreatmentPlanCommand command) {
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
