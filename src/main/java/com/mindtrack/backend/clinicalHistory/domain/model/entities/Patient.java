package com.mindtrack.backend.clinicalHistory.domain.model.entities;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
public class Patient extends AuditableAbstractAggregateRoot<Patient> {

    @Getter
    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Getter
    private boolean clinicalHistoryStatus;

    public Patient() {}

    public Patient(CreatePatientCommand command, Profile profile) {
        this.profile = profile;
        this.clinicalHistoryStatus = command.clinicalHistoryStatus();
    }

    public void updateClinicalHistoryStatus() {
        this.clinicalHistoryStatus = true;
    }
}
