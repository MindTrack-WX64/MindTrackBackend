package com.mindtrack.backend.session.interfaces.rest.resources;

import java.time.LocalDate;

public record NoteResource(
        String content,
        LocalDate creationDate
) {
}
