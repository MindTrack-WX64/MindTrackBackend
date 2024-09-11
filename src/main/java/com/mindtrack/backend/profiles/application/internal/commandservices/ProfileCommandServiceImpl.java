package com.mindtrack.backend.profiles.application.internal.commandservices;

import com.mindtrack.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.mindtrack.backend.profiles.domain.services.ProfileCommandService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var user = this.userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + command.userId() + " not found"));
        if (this.profileRepository.existsByEmail(command.email())) throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        var profile = new Profile(command, user);
        this.profileRepository.save(profile);

        return Optional.of(profile);
    }
}
