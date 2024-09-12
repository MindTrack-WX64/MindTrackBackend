package com.mindtrack.backend.session.domain.model.entities;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.session.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Professional extends AuditableAbstractAggregateRoot<Professional> {

    @NotBlank
    @Column(nullable = false)
    private String professionalType;

    @ManyToMany
    @JoinTable(
            name="professional_patients",
            joinColumns = @JoinColumn(name="professional_id"),
            inverseJoinColumns = @JoinColumn(name="patient_id"))
    private List<Patient> patients;

    public Professional() {
    }

    public Professional(CreateProfessionalCommand command) {
        this.professionalType = command.professionalType();
        this.patients = new ArrayList<Patient>();
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

}
