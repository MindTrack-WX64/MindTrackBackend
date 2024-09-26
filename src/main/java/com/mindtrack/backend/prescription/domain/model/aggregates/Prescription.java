package com.mindtrack.backend.prescription.domain.model.aggregates;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.prescription.domain.model.commands.AddPillsToDescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.commands.CreatePrescriptionCommand;
import com.mindtrack.backend.prescription.domain.model.entities.Pill;
import com.mindtrack.backend.session.domain.model.entities.Professional;
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

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @ElementCollection
    @CollectionTable(name = "prescription_pills", joinColumns = @JoinColumn(name = "prescription_id"))
    private List<Pill> pills;

    @Column(nullable = false)
    LocalDate startDate;

    @Column(nullable = false)
    LocalDate endDate;

    public Prescription() {}

    public Prescription(CreatePrescriptionCommand command, Patient patient, Professional professional) {
        this.patient = patient;
        this.professional = professional;
        this.pills = new ArrayList<Pill>();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
    }

    public void addPill(AddPillsToDescriptionCommand command) {
        Pill pill = new Pill(command.name(), command.description(), command.quantity(), command.frequency());
        this.pills.add(pill);
    }
}
