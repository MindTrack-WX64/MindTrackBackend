package com.mindtrack.backend.profiles.interfaces.acl;

import java.time.LocalDate;

/**
 * Profiles context facade
 */
public interface ProfilesContextFacade {

    /**
     * Creates a patient profile
     * @param fullName The full name of the patient
     * @param email The email of the patient
     * @param phone The phone number of the patient
     * @param birthDate The birthdate of the patient
     * @param userId The user id related to the patient
     * @return The id of the created patient
     */
    Long createPatient(String fullName,
                       String email,
                       String phone,
                       LocalDate birthDate,
                       Long userId);

    /**
     * Creates a professional profile
     * @param fullName The full name of the professional
     * @param email The email of the professional
     * @param phone The phone number of the professional
     * @param birthDate The birthdate of the professional
     * @param professionalType The type of professional
     * @param userId The user id related to the professional
     * @return The id of the created professional
     */
    Long createProfessional(String fullName,
                            String email,
                            String phone,
                            LocalDate birthDate,
                            String professionalType, Long userId);

    /**
     * Fetches a profile by patient id
     * @param patientId The id of the patient
     * @return The id of the profile
     */
    Long fetchProfileByPatientId(Long patientId);

    /**
     * Fetches a profile by professional id
     * @param professionalId The id of the professional
     * @return The id of the profile
     */
    Long fetchProfileByProfessionalId(Long professionalId);
}
