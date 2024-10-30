package com.mindtrack.backend.profiles.domain.model.aggregates;

import com.mindtrack.backend.profiles.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.shared.domain.aggregates.Profile;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Professional extends Profile {

    @NotBlank
    private String professionalType;

    @NotNull(message = "User ID is required")
    private Long userId;

    protected Professional() {
        super();
    }

    public Professional(CreateProfessionalCommand command, Long userId) {
        super(command.fullName(), command.email(), command.phone(), command.birthDate());
        this.professionalType = command.professionalType();
        this.userId = userId;
    }
}
