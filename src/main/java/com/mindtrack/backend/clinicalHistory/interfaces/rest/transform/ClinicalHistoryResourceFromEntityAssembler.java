package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.ClinicalHistoryResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;

public class ClinicalHistoryResourceFromEntityAssembler {
    public static ClinicalHistoryResource toResourceFromEntity(ClinicalHistory entity) {
        PatientResource patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(entity.getPatient());
        return new ClinicalHistoryResource(
                entity.getId(),
                patientResource,
                entity.getBackground(),
                entity.getConsultationReason(),
                entity.getConsultationDate(),
                entity.getSymptoms()
        );
    }
}
