package com.mindtrack.backend.iam.application.internal.commandservices;

import com.mindtrack.backend.iam.application.internal.outboundservices.acl.ExternalProfileService;
import com.mindtrack.backend.iam.application.internal.outboundservices.hashing.HashingService;
import com.mindtrack.backend.iam.application.internal.outboundservices.tokens.TokenService;
import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.iam.domain.model.commands.SignInCommand;
import com.mindtrack.backend.iam.domain.model.commands.SignUpCommand;
import com.mindtrack.backend.iam.domain.model.commands.SignUpPatientCommand;
import com.mindtrack.backend.iam.domain.model.commands.SignUpProfessionalCommand;
import com.mindtrack.backend.iam.domain.model.entities.Role;
import com.mindtrack.backend.iam.domain.model.valueobjects.Roles;
import com.mindtrack.backend.iam.domain.services.UserCommandService;
import com.mindtrack.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.mindtrack.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final ExternalProfileService externalProfileService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository, ExternalProfileService externalProfileService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpPatientCommand command) {
        if (this.userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Username already exists");
        }

        Role patientRole = this.roleRepository.findByName(Roles.valueOf("PATIENT"))
                .orElseThrow(() -> new RuntimeException("Patient role not found"));

        List<Role> roles = List.of(patientRole);

        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        this.userRepository.save(user);

        Optional<Long> patientId = this.externalProfileService.createPatient(
                command.fullName(), command.email(), command.phone(),
                command.birthDate(), user.getId()
        );

        if (patientId.isEmpty()) {
            throw new RuntimeException("Error creating patient profile");
        }

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(SignUpProfessionalCommand command) {
        if (this.userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Username already exists");
        }

        Role professionalRole = this.roleRepository.findByName(Roles.valueOf("PROFESSIONAL"))
                .orElseThrow(() -> new RuntimeException("Professional role not found"));

        List<Role> roles = List.of(professionalRole);

        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        this.userRepository.save(user);

        Optional<Long> professionalId = this.externalProfileService.createProfessional(
                command.fullName(), command.email(), command.phone(), command.birthDate(),
                command.professionalType(), user.getId()
        );

        if (professionalId.isEmpty()) {
            throw new RuntimeException("Error creating professional profile");
        }

        return Optional.of(user);
    }
}
