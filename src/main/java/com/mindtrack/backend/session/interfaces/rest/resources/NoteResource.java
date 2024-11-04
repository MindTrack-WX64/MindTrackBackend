package com.mindtrack.backend.session.interfaces.rest.resources;

public record NoteResource(
        String content,
        String creationDate
) {
}
