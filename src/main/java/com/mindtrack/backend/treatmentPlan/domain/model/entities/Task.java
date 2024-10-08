package com.mindtrack.backend.treatmentPlan.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String title;
    private final String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed;

    public Task() {
        this.title = null;
        this.description = null;
        this.startDate = null;
        this.endDate = null;
        this.completed = false;
    }

    public Task(String title, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
        this.title = title;
        this.description = description;
        this.startDate = null;
        this.endDate = null;
        this.completed = false;
    }

    public void startTask() {
        this.startDate = LocalDate.now();
    }

    public void finishTask() {
        this.endDate = LocalDate.now();
        this.completed = true;
    }

    public String getTaskInfo() {
        return ("Title: " + title + " - " + "Description: " + description + " - " + "Start Date: " + startDate + " - " + "End Date: " + endDate + " - " + "Completed: " + completed);
    }
}
