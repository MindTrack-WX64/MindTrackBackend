package com.mindtrack.backend.session.domain.model.entities;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.shared.domain.aggregates.Profile;
import com.mindtrack.backend.session.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class Professional extends Profile {

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private final User user;

    @NotBlank
    @Column(nullable = false)
    private final String professionalType;

    @ManyToMany
    @JoinTable(
            name="professional_patients",
            joinColumns = @JoinColumn(name="professional_id"),
            inverseJoinColumns = @JoinColumn(name="patient_id"))
    private final Set<Patient> patients;

    public Professional() {
        super();
        this.user = null;
        this.professionalType = null;
        this.patients = new HashSet<>();
    }

    public Professional(CreateProfessionalCommand command, User user) {
        super(command.fullName(), command.email(), command.phone(), command.birthDate());
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
        this.professionalType = command.professionalType();
        this.patients = new HashSet<>();
    }

    public void addPatient(Patient patient) {
        if (patient != null) {
            this.patients.add(patient);
        }
    }

}
