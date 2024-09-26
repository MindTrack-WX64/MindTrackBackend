package com.mindtrack.backend.treatmentPlan.domain.services;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.commands.*;

import java.util.Optional;

public interface TreatmentPlanCommandService {
    Optional<TreatmentPlan> handle(CreateTreatmentPlanCommand command);
    Optional<TreatmentPlan> handle(AddBiologicalFunctionCommand command);
    Optional<TreatmentPlan> handle(AddDiagnosticCommand command);
    Optional<TreatmentPlan> handle(AddPatientStateCommand command);
    Optional<TreatmentPlan> handle(AddPrescriptionCommand command);
    Optional<TreatmentPlan> handle(AddSessionCommand command);
    Optional<TreatmentPlan> handle(AddTaskCommand command);
    Optional<TreatmentPlan> handle(FinishTaskCommand command);
    Optional<TreatmentPlan> handle(StartTaskCommand command);
}
