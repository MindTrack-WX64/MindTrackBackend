package com.mindtrack.backend.iam.application.internal.outboundservices.acl;

import com.mindtrack.backend.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExternalProfileService {
    private final ProfilesContextFacade profileContextFacade;

    public ExternalProfileService(ProfilesContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Long createPatient(
                              String fullName,
                              String email,
                              String phone,
                              LocalDate birthDate,
                              Long userId) {
        return profileContextFacade.createPatient(
                fullName, email, phone, birthDate, userId);
    }

    public Long createProfessional(
                                    String fullName,
                                    String email,
                                    String phone,
                                    LocalDate birthDate,
                                    String professionalType,
                                    Long userId) {
        return profileContextFacade.createProfessional(
                fullName, email, phone, birthDate, professionalType, userId);
    }

}
