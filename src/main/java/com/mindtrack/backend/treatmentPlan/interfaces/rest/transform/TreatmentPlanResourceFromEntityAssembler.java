package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PrescriptionResource;
import com.mindtrack.backend.prescription.interfaces.rest.transform.PrescriptionResourceFromEntityAssembler;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;
import com.mindtrack.backend.session.interfaces.rest.resources.SessionResource;
import com.mindtrack.backend.session.interfaces.rest.transform.ProfessionalResourceFromEntityAssembler;
import com.mindtrack.backend.session.interfaces.rest.transform.SessionResourceFromEntityAssembler;
import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.BiologicalFunction;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Diagnostic;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.PatientState;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.TreatmentPlanResource;

import java.util.List;

public class TreatmentPlanResourceFromEntityAssembler {
    public static TreatmentPlanResource toResourceFromEntity(TreatmentPlan entity) {
        PatientResource patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(entity.getPatient());
        ProfessionalResource professionalResource = ProfessionalResourceFromEntityAssembler
                .toResourceFromEntity(entity.getProfessional());
        List<SessionResource> sessionResources = entity.getSessions().stream()
                .map(SessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        List<PrescriptionResource> prescriptionResources = entity.getPrescriptions().stream()
                .map(PrescriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        List<String> patientStates = entity.getPatientStates().stream().map(PatientState::getPatientStateInfo).toList();
        List<String> biologicalFunctions = entity.getBiologicalFunctions().stream()
                .map(BiologicalFunction::getBiologicalFunctionInfo).toList();
        List<String> diagnostics = entity.getDiagnostics().stream().map(Diagnostic::getDiagnosticInfo).toList();
        List<String> tasks = entity.getTasks().stream().map(Task::getTaskInfo).toList();
        return new TreatmentPlanResource(
                entity.getId(),
                patientResource,
                professionalResource,
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isFinished(),
                entity.getDescription(),
                sessionResources,
                prescriptionResources,
                patientStates,
                biologicalFunctions,
                diagnostics,
                tasks
        );
    }
}
