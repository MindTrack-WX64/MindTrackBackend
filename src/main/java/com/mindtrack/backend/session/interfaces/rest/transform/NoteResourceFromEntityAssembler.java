package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.session.domain.model.entities.Note;
import com.mindtrack.backend.session.interfaces.rest.resources.NoteResource;

public class NoteResourceFromEntityAssembler {
    public static NoteResource toResourceFromEntity(Note entity) {
        return new NoteResource(
                entity.getContent(),
                entity.getCreationDate()
        );
    }
}
