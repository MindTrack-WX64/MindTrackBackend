package com.mindtrack.backend.session.interfaces.rest.resources;

public record CreateNoteResource(
        String content,
        Long sessionId
) {
}
