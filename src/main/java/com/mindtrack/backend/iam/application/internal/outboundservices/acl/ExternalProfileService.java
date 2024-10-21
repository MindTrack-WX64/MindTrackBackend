package com.mindtrack.backend.iam.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExternalProfileService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Long createPatient(
                              String fullName,
                              String email,
                              String phone,
                              LocalDate birthDate,
                              boolean clinicalHistoryStatus,
                              Long userId) {
        return profileContextFacade.createPatient(
                fullName, email, phone, birthDate, clinicalHistoryStatus, userId);
    }

    public Long createProfessional(
                                    String fullName,
                                    String email,
                                    String phone,
                                    String birthDate,
                                    String professionalType,
                                    Long userId) {
        return profileContextFacade.createProfessional(
                fullName, email, phone, birthDate, professionalType, userId);
    }

}
