package com.mindtrack.backend.prescription.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Embeddable
@Getter
public class Pill {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quantity;

    @NotBlank
    @Column(nullable = false)
    private String frequency;

    public Pill() {}

    public Pill(String name, String description, int quantity, String frequency) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public String getPillInfo() {
        return ("Name: " + name + ", Description: " + description + ", Quantity: " + quantity + ", Frequency: " + frequency);
    }
}
