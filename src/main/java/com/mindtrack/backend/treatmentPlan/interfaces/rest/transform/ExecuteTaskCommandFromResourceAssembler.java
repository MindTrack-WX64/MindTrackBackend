package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.commands.ExecuteTaskCommand;

public class ExecuteTaskCommandFromResourceAssembler {
    public static ExecuteTaskCommand toCommandFromResource(String action, Long taskId, Long treatmentPlanId) {
        return new ExecuteTaskCommand(action, taskId, treatmentPlanId);
    }
}
