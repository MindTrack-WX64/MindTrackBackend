package com.mindtrack.backend.clinicalHistory.domain.model.entities;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.shared.domain.aggregates.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Patient extends Profile {

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private final User user;

    @Getter
    private boolean clinicalHistoryStatus;

    public Patient() {
        super();
        this.user = null;
        this.clinicalHistoryStatus = false;
    }

    public Patient(CreatePatientCommand command, User user) {
        super(command.fullName(), command.email(), command.phone(), command.birthDate());
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
        this.clinicalHistoryStatus = false;
    }

    public void updateClinicalHistoryStatus() {
        this.clinicalHistoryStatus = true;
    }
}
