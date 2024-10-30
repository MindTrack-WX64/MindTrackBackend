package com.mindtrack.backend.clinicalHistory.domain.model.aggregates;

import com.mindtrack.backend.clinicalHistory.domain.model.commands.AddSymptomCommand;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreateClinicalHistoryCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ClinicalHistory extends AuditableAbstractAggregateRoot<ClinicalHistory> {

    private Long patientId;

    @NotBlank
    @Column(nullable = false)
    String background;

    @NotBlank
    @Column(nullable = false)
    String consultationReason;

    @Column(nullable = false)
    LocalDate consultationDate;

    @ElementCollection
    @Column(nullable = false)
    List<String> symptoms;

    public ClinicalHistory() {
    }

    public ClinicalHistory(CreateClinicalHistoryCommand command) {
        this.patientId = command.patientId();
        this.background = command.background();
        this.consultationReason = command.consultationReason();
        this.consultationDate = command.consultationDate();
        this.symptoms = new ArrayList<String>();
    }

    public void addSymptom(AddSymptomCommand command) {
        this.symptoms.add(command.symptom());
    }
}
