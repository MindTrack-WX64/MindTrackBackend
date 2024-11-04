package com.mindtrack.backend.iam.application.internal.outboundservices.acl;

import com.mindtrack.backend.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExternalProfileService {
    private final ProfilesContextFacade profileContextFacade;

    public ExternalProfileService(ProfilesContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Long> createPatient(
                              String fullName,
                              String email,
                              String phone,
                              String birthDate,
                              Long userId) {
        var profileId = profileContextFacade.createPatient(
                fullName, email, phone, birthDate, userId);

        return profileId == 0L ? Optional.empty() : Optional.of(profileId);
    }

    public Optional<Long> createProfessional(
                                    String fullName,
                                    String email,
                                    String phone,
                                    String birthDate,
                                    String professionalType,
                                    Long userId) {
        var profileId = profileContextFacade.createProfessional(
                fullName, email, phone, birthDate, professionalType, userId);

        return profileId == 0L ? Optional.empty() : Optional.of(profileId);
    }

}
