package com.mindtrack.backend.profiles.domain.model.aggregates;

import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.shared.domain.aggregates.Profile;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Patient extends Profile {

    private boolean clinicalHistoryStatus;

    @NotNull(message = "User cannot be null")
    private Long userId;

    protected Patient() {
        super();
    }

    public Patient(CreatePatientCommand command, Long userId) {
        super(command.fullName(), command.email(), command.phone(), command.birthDate());
        this.userId = userId;
        this.clinicalHistoryStatus = false;
    }
}
