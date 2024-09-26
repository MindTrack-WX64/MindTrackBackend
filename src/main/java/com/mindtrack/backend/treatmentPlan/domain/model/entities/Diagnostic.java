package com.mindtrack.backend.treatmentPlan.domain.model.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class Diagnostic {
    private final String name;
    private final String description;
    private final LocalDate date;

    public Diagnostic() {
        this.name = null;
        this.description = null;
        this.date = null;
    }

    public Diagnostic(String name, String description, LocalDate date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getDiagnosticInfo() {
        return ("Name: " + name + " - " + "Description: " + description + " - " + "Date: " + date);
    }
}
