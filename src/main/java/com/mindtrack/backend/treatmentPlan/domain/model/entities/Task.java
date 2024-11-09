package com.mindtrack.backend.treatmentPlan.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private boolean status;

    public Task() {
        this.title = null;
        this.description = null;
        this.startDate = null;
        this.endDate = null;
        this.status = false;
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
        this.status = false;
    }

    public void startTask() {
        this.startDate = LocalDate.now();
    }

    public void finishTask() {
        this.endDate = LocalDate.now();
        this.status = true;
    }

    public String getTaskInfo() {
        return ("Title: " + title + " - " + "Description: " + description + " - " + "Start Date: " + startDate + " - "
                + "End Date: " + endDate + " - " + "Status: " + status);
    }
}
