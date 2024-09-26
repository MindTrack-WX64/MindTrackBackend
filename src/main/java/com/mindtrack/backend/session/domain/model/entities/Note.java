package com.mindtrack.backend.session.domain.model.entities;

import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class Note {
    private String content;
    private LocalDate creationDate;

    public Note() {}

    public Note(String content, LocalDate creationDate) {
        this.content = content;
        this.creationDate = creationDate;
    }
}
