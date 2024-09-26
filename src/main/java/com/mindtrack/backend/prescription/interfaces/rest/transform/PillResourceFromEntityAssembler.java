package com.mindtrack.backend.prescription.interfaces.rest.transform;

import com.mindtrack.backend.prescription.domain.model.entities.Pill;
import com.mindtrack.backend.prescription.interfaces.rest.resources.PillResource;

public class PillResourceFromEntityAssembler {
    public static PillResource toResourceFromEntity(Pill entity) {
        return new PillResource(
                entity.getName(),
                entity.getDescription(),
                entity.getQuantity(),
                entity.getFrequency()
        );
    }
}
