package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.BiologicalFunction;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Diagnostic;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.PatientState;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.TreatmentPlanResource;

import java.util.List;

public class TreatmentPlanResourceFromEntityAssembler {
    public static TreatmentPlanResource toResourceFromEntity(TreatmentPlan entity) {
        List<String> patientStates = entity.getPatientStates().stream().map(PatientState::getPatientStateInfo).toList();
        List<String> biologicalFunctions = entity.getBiologicalFunctions().stream()
                .map(BiologicalFunction::getBiologicalFunctionInfo).toList();
        List<String> diagnostics = entity.getDiagnostics().stream().map(Diagnostic::getDiagnosticInfo).toList();
        List<String> tasks = entity.getTasks().stream().map(Task::getTaskInfo).toList();
        return new TreatmentPlanResource(
                entity.getId(),
                entity.getPatientId(),
                entity.getProfessionalId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isFinished(),
                entity.getDescription(),
                patientStates,
                biologicalFunctions,
                diagnostics,
                tasks
        );
    }
}
