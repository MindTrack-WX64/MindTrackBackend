package com.mindtrack.backend.session.interfaces.rest.transform;

import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateNoteResource;

public class CreateNoteCommandFromResourceAssembler {
    public static CreateNoteCommand toCommandFromResource(CreateNoteResource resource, Long id) {
        return new CreateNoteCommand(
                resource.content(),
                id
        );
    }
}
