package com.mindtrack.backend.session.domain.model.commands;

public record CreateNoteCommand(
        String content,
        Long sessionId
) {
}
