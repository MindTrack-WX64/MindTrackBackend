package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.interfaces.rest.resources.NoteResource;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;
import com.mindtrack.backend.session.interfaces.rest.resources.SessionResource;

import java.util.List;

public class SessionResourceFromEntityAssembler {
    public static SessionResource toResourceFromEntity(Session entity) {
        PatientResource patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(entity.getPatient());
        ProfessionalResource professionalResource = ProfessionalResourceFromEntityAssembler.toResourceFromEntity(entity.getProfessional());
        List<NoteResource> noteResources = entity.getNotes().stream().map(NoteResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new SessionResource(
                entity.getId(),
                patientResource,
                professionalResource,
                entity.getSessionDate(),
                noteResources
        );
    }
}
