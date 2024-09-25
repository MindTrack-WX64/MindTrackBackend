package com.mindtrack.backend.treatmentPlan.domain.model.entities;

import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.MoodStates;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class PatientState {
    private LocalDate date;
    private MoodStates moodState;

    public PatientState() {
    }

    public PatientState(LocalDate date, MoodStates moodState) {
        this.date = date;
        this.moodState = moodState;
    }

    public String getMoodStateString() {
        return moodState.toString();
    }
}
