package com.mindtrack.backend.prescription.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Pill {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quantity;

    public Pill() {}

    public Pill(String name, String description, int quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getPillInfo() {
        return ("Name: " + name + ", Description: " + description + ", Quantity: " + quantity);
    }
}
