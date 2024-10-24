package com.mindtrack.backend.prescription.domain.model.aggregates;

import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.entities.Pill;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Prescription extends AuditableAbstractAggregateRoot<Prescription> {

    @NotNull
    private Long patientId;

    @NotNull
    private Long professionalId;

    @ElementCollection
    @CollectionTable(name = "prescription_pills", joinColumns = @JoinColumn(name = "prescription_id"))
    private List<Pill> pills;

    @Column(nullable = false)
    LocalDate startDate;

    @Column(nullable = false)
    LocalDate endDate;

    public Prescription() {}

    public Prescription(CreatePrescriptionCommand command) {
        this.patientId = command.patientId();
        this.professionalId = command.professionalId();
        this.pills = new ArrayList<Pill>();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
    }

    public void addPill(AddPillsToDescriptionCommand command) {
        Pill pill = new Pill(command.name(), command.description(), command.quantity(), command.frequency());
        this.pills.add(pill);
    }
}
