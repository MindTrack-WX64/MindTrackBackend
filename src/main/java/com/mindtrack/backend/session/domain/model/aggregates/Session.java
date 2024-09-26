package com.mindtrack.backend.session.domain.model.aggregates;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.session.domain.model.commands.CreateNoteCommand;
import com.mindtrack.backend.session.domain.model.commands.CreateSessionCommand;
import com.mindtrack.backend.session.domain.model.entities.Note;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Session extends AuditableAbstractAggregateRoot<Session> {

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @Column(nullable = false)
    private LocalDate sessionDate;

    @ElementCollection
    @CollectionTable(name = "session_notes", joinColumns = @JoinColumn(name = "session_id"))
    private List<Note> notes;

    public Session() {}

    public Session(CreateSessionCommand command, Patient patient, Professional professional) {
        this.patient = patient;
        this.professional = professional;
        this.sessionDate = command.sessionDate();
        this.notes = new ArrayList<Note>();
    }

    public void addNote(CreateNoteCommand command) {
        Note note = new Note(command.content(), this.sessionDate);
        this.notes.add(note);
    }
}
