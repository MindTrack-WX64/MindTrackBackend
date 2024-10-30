package com.mindtrack.backend.treatmentPlan.domain.model.aggregates;

import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.treatmentPlan.domain.model.commands.*;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.BiologicalFunction;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Diagnostic;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.PatientState;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
public class TreatmentPlan extends AuditableAbstractAggregateRoot<TreatmentPlan> {
    @NotNull
    private Long patientId;

    @NotNull
    private Long professionalId;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "treatment_tasks", joinColumns = @JoinColumn(name = "treatment_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;


    public TreatmentPlan() {
        this.sessions = new HashSet<>();
        this.prescriptions = new HashSet<>();
    }

    public TreatmentPlan(CreateTreatmentPlanCommand command) {
        this.patientId = command.patientId();
        this.professionalId = command.professionalId();
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

    public void addBiologicalFunction(AddBiologicalFunctionCommand command) {
        if (command.hunger() == 0 || command.sleep() == 0 || command.hydration() == 0 || command.energy() == 0) {
            throw new IllegalArgumentException("The hunger, sleep, hydration and energy values cannot be 0");
        }

        // Verificar si ya existe una función biológica para la fecha proporcionada
        boolean exists = biologicalFunctions.stream()
                .anyMatch(biologicalFunction -> biologicalFunction.getDate().equals(command.date()));

        if (exists) {
            throw new IllegalArgumentException("A biological function already exists for this date");
        }

        // Si no existe, agregar la nueva función biológica
        BiologicalFunction biologicalFunction = new BiologicalFunction(command.date(), command.hunger(), command.sleep(), command.hydration(), command.energy());
        biologicalFunctions.add(biologicalFunction);
    }

    public void addDiagnostic(AddDiagnosticCommand command) {
        if (command.date() == null || command.description() == null || command.name() == null) {
            throw new IllegalArgumentException("The date, description and name cannot be null");
        }
        Diagnostic diagnostic = new Diagnostic(command.name(), command.description(), command.date());
        diagnostics.add(diagnostic);
    }

    public void addTask(AddTaskCommand command) {
        if (command.title() == null || command.description() == null) {
            throw new IllegalArgumentException("The title and description cannot be null");
        }
        Task task = new Task(command.title(), command.description());
        tasks.add(task);
    }

    public void startTask(StartTaskCommand command) {
        if (command.taskId() == null) {
            throw new IllegalArgumentException("The task id cannot be null");
        }

        Task task = tasks.stream()
                .filter(t -> t.getId().equals(command.taskId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The task does not exist"));

        task.startTask();
    }

    public void finishTask(FinishTaskCommand command) {
        if (command.taskId() == null) {
            throw new IllegalArgumentException("The task id cannot be null");
        }

        Task task = tasks.stream()
                .filter(t -> t.getId().equals(command.taskId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The task does not exist"));

        task.finishTask();
    }

    public void addSession(Session session) {
        boolean exists = sessions.stream()
                .anyMatch(s -> s.getId().equals(session.getId()));

        if (exists) {
            throw new IllegalArgumentException("The session already exists");
        }

        sessions.add(session);
    }

    public void addPrescription(Prescription prescription) {
        boolean exists = prescriptions.stream()
                .anyMatch(p -> p.getId().equals(prescription.getId()));

        if (exists) {
            throw new IllegalArgumentException("The prescription already exists");
        }

        prescriptions.add(prescription);
    }

}
