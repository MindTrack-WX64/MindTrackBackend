package com.mindtrack.backend.treatmentPlan.domain.model.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class BiologicalFunction {
    private final LocalDate date;


    private final int hunger;
    private final int sleep;
    private final int hydration;
    private final int energy;

    public BiologicalFunction() {
        this.date = null;
        this.hunger = 0;
        this.sleep = 0;
        this.hydration = 0;
        this.energy = 0;
    }

    public BiologicalFunction(int hunger, int sleep, int hydration, int energy) {
        this.date = LocalDate.now();
        this.hunger = hunger;
        this.sleep = sleep;
        this.hydration = hydration;
        this.energy = energy;
    }

    public String getBiologicalFunctionInfo() {
        return ("Date: " + date + " - " + "Hunger: " + hunger + " - " + "Sleep: " + sleep + " - " + "Hydration: " + " - " + hydration + "Energy: " + " - " + energy);
    }
}
