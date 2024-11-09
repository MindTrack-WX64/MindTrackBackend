package com.mindtrack.backend.session.application.internal.outboundservices.acl;

import com.mindtrack.backend.treatmentPlan.interfaces.acl.TreatmentPlansContextFacade;
import org.springframework.stereotype.Service;

/**
 * ExternalTreatmentPlanService
 */
@Service
public class ExternalTreatmentPlanService {
    private final TreatmentPlansContextFacade treatmentPlansContextFacade;

    public ExternalTreatmentPlanService(TreatmentPlansContextFacade treatmentPlansContextFacade) {
        this.treatmentPlansContextFacade = treatmentPlansContextFacade;
    }

    public boolean verifyTreatmentPlanExists(Long treatmentPlanId) {
        return treatmentPlansContextFacade.verifyTreatmentPlanExists(treatmentPlanId);
    }
}
