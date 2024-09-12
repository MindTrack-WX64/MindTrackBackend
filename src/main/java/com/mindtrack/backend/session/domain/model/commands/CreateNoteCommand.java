package com.mindtrack.backend.session.domain.model.commands;

import java.time.LocalDate;

public record CreateNoteCommand(
        String content,
        Long sessionId
) {
}
