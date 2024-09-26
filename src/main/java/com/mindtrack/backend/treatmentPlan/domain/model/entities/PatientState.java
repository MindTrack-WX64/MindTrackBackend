package com.mindtrack.backend.treatmentPlan.domain.model.entities;

import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.MoodStates;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class PatientState {
    private final LocalDate date;
    private final MoodStates moodState;

    public PatientState() {
        this.date = null;
        this.moodState = null;
    }

    public PatientState(LocalDate date, MoodStates moodState) {
        this.date = date;
        this.moodState = moodState;
    }

    public String getMoodStateString() {
        return moodState.toString();
    }

    public String getPatientStateInfo() {
        return ("Date: " + date + " - " + "Mood State: " + moodState);
    }
}
