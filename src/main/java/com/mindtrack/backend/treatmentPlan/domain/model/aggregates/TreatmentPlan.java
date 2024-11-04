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

        // Verify if a mood state already exists for the current date
        boolean exists = patientStates.stream()
                .anyMatch(state -> state.getDate().equals(LocalDate.now()));

        if (exists) {
            throw new IllegalArgumentException("A patient state already exists for this date");
        }

        // If it does not exist, add the new mood state
        PatientState newState = new PatientState(command.moodState());
        patientStates.add(newState);
    }

    public void addBiologicalFunction(AddBiologicalFunctionCommand command) {

        // Verify if a biological function already exists for the current date
        boolean exists = biologicalFunctions.stream()
                .anyMatch(biologicalFunction -> biologicalFunction.getDate().equals(LocalDate.now()));

        if (exists) {
            throw new IllegalArgumentException("A biological function already exists for this date");
        }

        // If it does not exist, add the new biological function
        BiologicalFunction biologicalFunction = new BiologicalFunction(command.hunger(), command.sleep(), command.hydration(), command.energy());
        biologicalFunctions.add(biologicalFunction);
    }

    public void addDiagnostic(AddDiagnosticCommand command) {
        Diagnostic diagnostic = new Diagnostic(command.name(), command.description());
        diagnostics.add(diagnostic);
    }

    public void addTask(AddTaskCommand command) {
        Task task = new Task(command.title(), command.description());
        tasks.add(task);
    }

    public void executeTask(ExecuteTaskCommand command) {
        var task = findTask(command.taskId());

        switch (command.action()) {
            case "start":
                if (!task.isStatus()) {
                    task.startTask();
                } else {
                    throw new IllegalArgumentException("Task already started");
                }
                break;
            case "finish":
                if (task.isStatus()) {
                    task.finishTask();
                } else {
                    throw new IllegalArgumentException("Task not started");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + command.action());
        }
    }

    private Task findTask(Long taskId) {
        return tasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
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
