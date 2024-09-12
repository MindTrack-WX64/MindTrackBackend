package com.mindtrack.backend.prescription.domain.model.aggregates;

import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.entities.Pill;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Prescription extends AuditableAbstractAggregateRoot<Prescription> {

    @ElementCollection
    @CollectionTable(name = "prescription_pills", joinColumns = @JoinColumn(name = "prescription_id"))
    private List<Pill> pills;

    @NotBlank
    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    LocalDate startDate;

    @Column(nullable = false)
    LocalDate endDate;

    public Prescription() {}

    public Prescription(CreatePrescriptionCommand command) {
        this.pills = new ArrayList<Pill>();
        this.frequency = command.frequency();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
    }

    public void addPill(AddPillsToDescriptionCommand command) {
        Pill pill = new Pill(command.name(), command.description(), command.quantity());
        this.pills.add(pill);
    }
}
