package com.mindtrack.backend.session.domain.model.aggregates;

import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.domain.model.entities.Note;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import com.mindtrack.backend.shared.domain.valueobjects.TreatmentPlanId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Session extends AuditableAbstractAggregateRoot<Session> {

    @NotNull
    private Long patientId;

    @NotNull
    private Long professionalId;

    @Embedded
    private TreatmentPlanId treatmentPlanId;

    @Column(nullable = false)
    private String sessionDate;

    @ElementCollection
    @CollectionTable(name = "session_notes", joinColumns = @JoinColumn(name = "session_id"))
    private List<Note> notes;

    public Session() {}

    public Session(CreateSessionCommand command) {
        this.patientId = command.patientId();
        this.professionalId = command.professionalId();
        this.sessionDate = command.sessionDate();
        this.treatmentPlanId = new TreatmentPlanId(0L);
        this.notes = new ArrayList<Note>();
    }

    public Session(CreateSessionCommand command, Long treatmentPlanId) {
        this.patientId = command.patientId();
        this.professionalId = command.professionalId();
        this.sessionDate = command.sessionDate();
        this.treatmentPlanId = new TreatmentPlanId(treatmentPlanId);
        this.notes = new ArrayList<Note>();
    }

    public void addNote(CreateNoteCommand command) {
        Note note = new Note(command.content(), this.sessionDate);
        this.notes.add(note);
    }

    public Long getTreatmentPlanId() {
        return this.treatmentPlanId.treatmentPlanId();
    }
}
