package com.mindtrack.backend.treatmentPlan.interfaces.rest.transform;

import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource toResourceFromEntity(Task entity, Long id) {
        return new TaskResource(
                entity.getId(),
                id,
                entity.getTitle(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isStatus()
        );
    }
}
