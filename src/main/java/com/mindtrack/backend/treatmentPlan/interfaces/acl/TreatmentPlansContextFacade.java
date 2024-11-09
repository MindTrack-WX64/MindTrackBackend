package com.mindtrack.backend.treatmentPlan.interfaces.acl;

/**
 * TreatmentPlansContextFacade
 */
public interface TreatmentPlansContextFacade {

    /**
     * Verify if treatment plan exists
     * @param treatmentPlanId the treatment plan id to verify
     * @return true if treatment plan exists, false otherwise
     */
    boolean verifyTreatmentPlanExists(Long treatmentPlanId);
}
