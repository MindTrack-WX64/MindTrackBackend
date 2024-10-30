package com.mindtrack.backend.clinicalHistory.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.ClinicalHistoryResource;

public class ClinicalHistoryResourceFromEntityAssembler {
    public static ClinicalHistoryResource toResourceFromEntity(ClinicalHistory entity) {
        return new ClinicalHistoryResource(
                entity.getId(),
                entity.getPatientId(),
                entity.getBackground(),
                entity.getConsultationReason(),
                entity.getConsultationDate(),
                entity.getSymptoms()
        );
    }
}
