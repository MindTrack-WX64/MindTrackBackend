package com.mindtrack.backend.profiles.domain.services;

import com.mindtrack.backend.profiles.domain.model.aggregates.Profile;
import com.mindtrack.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfileByEmailQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}
