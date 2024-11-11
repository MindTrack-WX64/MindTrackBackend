package com.mindtrack.backend.profiles.domain.model.aggregates;

import com.mindtrack.backend.profiles.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.profiles.domain.model.valueobjetcs.Profile;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
public class Professional extends Profile {

    @NotBlank
    private String professionalType;

    public Professional() {
        super();
    }

    public Professional(CreateProfessionalCommand command) {
        super(command.fullName(), command.email(), command.phone(), command.birthDate(), command.userId());
        this.professionalType = command.professionalType();
    }
}
