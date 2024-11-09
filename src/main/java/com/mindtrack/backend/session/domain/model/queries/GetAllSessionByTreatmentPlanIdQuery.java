package com.mindtrack.backend.session.domain.model.queries;

import com.mindtrack.backend.shared.domain.valueobjects.TreatmentPlanId;

public record GetAllSessionByTreatmentPlanIdQuery(Long treatmentPlanId) {
}
