package com.mindtrack.backend.profiles.application.internal.queryservices;

import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.mindtrack.backend.profiles.domain.services.ProfileQueryService;
import com.mindtrack.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        System.out.println("ProfileQueryServiceImpl.handle" + query.email());
        return profileRepository.findByEmail(query.email());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}
