package com.mindtrack.backend.session.domain.model.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Note {
    private String content;
    private String creationDate;

    public Note() {}

    public Note(String content, String creationDate) {
        this.content = content;
        this.creationDate = creationDate;
    }
}
