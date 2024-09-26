package com.mindtrack.backend.session.domain.model.queries;

import java.time.LocalDate;

public record GetAllSessionBySessionDateQuery(
        LocalDate sessionDate
) {
}
