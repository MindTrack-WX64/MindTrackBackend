package com.mindtrack.backend.profiles.domain.model.aggregates;

import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.profiles.domain.model.valueobjetcs.Profile;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Patient extends Profile {

    private boolean clinicalHistoryStatus;

    protected Patient() {
        super();
    }

    public Patient(CreatePatientCommand command) {
        super(command.fullName(), command.email(), command.phone(), command.birthDate(), command.userId());
        this.clinicalHistoryStatus = false;
    }
}
