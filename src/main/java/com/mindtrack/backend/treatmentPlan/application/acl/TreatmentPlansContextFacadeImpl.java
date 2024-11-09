package com.mindtrack.backend.treatmentPlan.application.acl;

import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetTreatmentPlanByIdQuery;
import com.mindtrack.backend.treatmentPlan.domain.services.TreatmentPlanQueryService;
import com.mindtrack.backend.treatmentPlan.interfaces.acl.TreatmentPlansContextFacade;
import org.springframework.stereotype.Service;

/**
 * TreatmentPlans Context Facade Implementation
 */
@Service
public class TreatmentPlansContextFacadeImpl implements TreatmentPlansContextFacade {

    private final TreatmentPlanQueryService treatmentPlanQueryService;

    public TreatmentPlansContextFacadeImpl(TreatmentPlanQueryService treatmentPlanQueryService) {
        this.treatmentPlanQueryService = treatmentPlanQueryService;
    }

    /**
     * Verify if a treatment plan exists
     * @param treatmentPlanId the treatment plan id to verify
     * @return true if the treatment plan exists, false otherwise
     */
    @Override
    public boolean verifyTreatmentPlanExists(Long treatmentPlanId) {
        var query = new GetTreatmentPlanByIdQuery(treatmentPlanId);
        var treatmentPlan = this.treatmentPlanQueryService.handle(query);
        return treatmentPlan.isPresent();
    }
}
