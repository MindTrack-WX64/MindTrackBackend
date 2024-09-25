package com.mindtrack.backend.treatmentPlan.domain.model.aggregates;

import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.treatmentPlan.domain.model.commands.AddPatientStateCommand;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.BiologicalFunction;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Diagnostic;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.PatientState;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import com.mindtrack.backend.treatmentPlan.domain.model.commands.CreateTreatmentPlanCommand;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
public class TreatmentPlan extends AuditableAbstractAggregateRoot<TreatmentPlan> {
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isFinished;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "treatment_sessions", joinColumns = @JoinColumn(name = "treatment_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private Set<Session> sessions;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "treatment_prescriptions", joinColumns = @JoinColumn(name = "treatment_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "prescription_id"))
    private Set<Prescription> prescriptions;

    @ElementCollection
    @CollectionTable(name="treatment_patientStates", joinColumns=@JoinColumn(name="treatment_plan_id"))
    private List<PatientState> patientStates;

    @ElementCollection
    @CollectionTable(name="treatment_biologicalFunctions", joinColumns=@JoinColumn(name="treatment_plan_id"))
    private List<BiologicalFunction> biologicalFunctions;

    @ElementCollection
    @CollectionTable(name="treatment_diagnostics", joinColumns=@JoinColumn(name="treatment_plan_id"))
    private List<Diagnostic> diagnostics;

    @ElementCollection
    @CollectionTable(name="treatment_tasks", joinColumns=@JoinColumn(name="treatment_plan_id"))
    private List<Task> tasks;


    public TreatmentPlan() {
        this.sessions = new HashSet<>();
        this.prescriptions = new HashSet<>();
    }

    public TreatmentPlan(CreateTreatmentPlanCommand command, Patient patient, Professional professional) {
        this.patient = patient;
        this.professional = professional;
        this.startDate = LocalDate.now();
        this.endDate = null;
        this.isFinished = false;
        this.description = command.description();
        this.sessions = new HashSet<>();
        this.prescriptions = new HashSet<>();
        this.patientStates = new ArrayList<PatientState>();
        this.biologicalFunctions = new ArrayList<BiologicalFunction>();
        this.diagnostics = new ArrayList<Diagnostic>();
        this.tasks = new ArrayList<Task>();
    }

    public void addPatientState(AddPatientStateCommand command) {
        if (command.moodState() == null || command.date() == null) {
            throw new IllegalArgumentException("The mood state and date cannot be null");
        }

        // Verificar si ya existe un estado para la fecha proporcionada
        boolean exists = patientStates.stream()
                .anyMatch(state -> state.getDate().equals(command.date()));

        if (exists) {
            throw new IllegalArgumentException("A patient state already exists for this date");
        }

        // Si no existe, agregar el nuevo estado del paciente
        PatientState newState = new PatientState(command.date(), command.moodState());
        patientStates.add(newState);
    }

}
