package com.mindtrack.backend.profiles.domain.model.aggregates;

import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String email;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String phone;

    @Getter
    @Column(nullable = false)
    private LocalDate birthDate;

    public Profile() {}

    public Profile(CreateProfileCommand command, User user) {
        this.userId = user;
        this.fullName = command.fullName();
        this.email = command.email();
        this.phone = command.phone();
        this.birthDate = command.birthDate();
    }
}
