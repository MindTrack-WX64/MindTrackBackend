package com.mindtrack.backend.profiles.domain.model.valueobjetcs;

import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Getter
    @NotBlank
    @Column(nullable = false)
    private final String fullName;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private final String email;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private final String phone;

    @Getter
    @Column(nullable = false)
    private final String birthDate;

    @Getter
    @Column(nullable = false)
    private final Long userId;

    // Constructor
    public Profile(String fullName, String email, String phone, String birthDate, Long userId) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.userId = userId;
    }

    protected Profile() {
        this.fullName = null;
        this.email = null;
        this.phone = null;
        this.birthDate = null;
        this.userId = null;
    }
}
