package com.mindtrack.backend.treatmentPlan.interfaces.rest;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.commands.ExecuteTaskCommand;
import com.mindtrack.backend.treatmentPlan.domain.model.entities.Task;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetAllTreatmentPlanByPatientIdQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetAllTreatmentPlanByProfessionalIdQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetTreatmentPlanByIdQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetTreatmentPlanStatisticsDataQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.TreatmentPlanStatistics;
import com.mindtrack.backend.treatmentPlan.domain.services.TreatmentPlanCommandService;
import com.mindtrack.backend.treatmentPlan.domain.services.TreatmentPlanQueryService;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.resources.*;
import com.mindtrack.backend.treatmentPlan.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server side"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The request was not authorized"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "The request was forbidden"),
})
@RestController
@RequestMapping(value ="/api/v1/treatment-plans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Treatment Plan", description = "The Treatment Plan Controller")
public class TreatmentPlanController {
    private final TreatmentPlanCommandService treatmentPlanCommandService;
    private final TreatmentPlanQueryService treatmentPlanQueryService;

    public TreatmentPlanController(TreatmentPlanCommandService treatmentPlanCommandService, TreatmentPlanQueryService treatmentPlanQueryService) {
        this.treatmentPlanCommandService = treatmentPlanCommandService;
        this.treatmentPlanQueryService = treatmentPlanQueryService;
    }

    @Operation(summary = "Create a new treatment plan", description = "Create a new treatment plan with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The treatment plan was created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping
    public ResponseEntity<TreatmentPlanResource> createTreatmentPlan(@RequestBody CreateTreatmentPlanResource treatmentPlanResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(CreateTreatmentPlanCommandFromResourceAssembler.toCommandFromResource(treatmentPlanResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(CREATED).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add biological function to treatment plan", description = "Add a biological function to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The biological function was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/biological-function")
    public ResponseEntity<TreatmentPlanResource> addBiologicalFunction(
            @RequestBody AddBiologicalFunctionResource biologicalFunctionResource,
            @PathVariable Long treatmentPlanId) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService
                .handle(AddBiologicalFunctionsCommandFromResourceAssembler.toCommandFromResource(biologicalFunctionResource, treatmentPlanId));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add diagnostic to treatment plan", description = "Add a diagnostic to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The diagnostic was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/diagnostic")
    public ResponseEntity<TreatmentPlanResource> addDiagnostic(@RequestBody AddDiagnosticResource diagnosticResource, @PathVariable Long treatmentPlanId) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService
                .handle(AddDiagnosticCommandFromResourceAssembler.toCommandFromResource(diagnosticResource, treatmentPlanId));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add patient state to treatment plan", description = "Add a patient state to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The patient state was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/patient-state")
    public ResponseEntity<TreatmentPlanResource> addPatientState(
            @Param("moodState") String moodState,
            @PathVariable Long treatmentPlanId
    ) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService
                .handle(AddPatientStateCommandFromResourceAssembler.toCommandFromResource(moodState, treatmentPlanId));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add prescription to treatment plan", description = "Add a prescription to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The prescription was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/prescription")
    public ResponseEntity<TreatmentPlanResource> addPrescription(
            @Param("prescriptionId") Long prescriptionId,
            @PathVariable Long treatmentPlanId
    ) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService
                .handle(AddPrescriptionCommandFromResourceAssembler.toCommandFromResource(prescriptionId, treatmentPlanId));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add session to treatment plan", description = "Add a session to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The session was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/session")
    public ResponseEntity<TreatmentPlanResource> addSession(
            @Param("sessionId") Long sessionId,
            @PathVariable Long treatmentPlanId
    ) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService
                .handle(AddSessionCommandFromResourceAssembler.toCommandFromResource(sessionId, treatmentPlanId));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add task to treatment plan", description = "Add a task to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The task was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/task")
    public ResponseEntity<TreatmentPlanResource> addTask(@RequestBody AddTaskResource taskResource, @PathVariable Long treatmentPlanId) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService
                .handle(AddTaskCommandFromResourceAssembler.toCommandFromResource(taskResource, treatmentPlanId));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Execute an action of a task",
            description = "Execute an action of a task of the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The action was executed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{treatmentPlanId}/task/{taskId}/execute")
    public ResponseEntity<TaskResource> executeTaskAction(
            @RequestParam("action") String action,
            @PathVariable Long treatmentPlanId,
            @PathVariable Long taskId
    ) {
        // Convierte el comando de recurso
        ExecuteTaskCommand command = ExecuteTaskCommandFromResourceAssembler.toCommandFromResource(action, treatmentPlanId, taskId);

        // Maneja la ejecución de la tarea
        try {
            Optional<Task> task = this.treatmentPlanCommandService.handle(command);

            return task.map(t -> TaskResourceFromEntityAssembler.toResourceFromEntity(t, treatmentPlanId))
                    .map(resource -> ResponseEntity.ok(resource))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (Exception e) {
            // Manejo de errores más específico puede ser útil
            return ResponseEntity.badRequest().body(null); // Podrías loguear el error aquí
        }
    }


    @Operation(summary = "Get treatment plan by id", description = "Get the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The treatment plan was found successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<TreatmentPlanResource> getTreatmentPlanById(@PathVariable Long id) {
        var query = new GetTreatmentPlanByIdQuery(id);
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanQueryService.handle(query);

        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all treatment plans by patient id", description = "Get all treatment plans with the given patient id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The treatment plans were found successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<TreatmentPlanResource>> getAllTreatmentPlanByPatientFullName(@PathVariable Long id) {
        var query = new GetAllTreatmentPlanByPatientIdQuery(id);
        List<TreatmentPlan> treatmentPlans = this.treatmentPlanQueryService.handle(query);

        if (treatmentPlans.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<TreatmentPlanResource> treatmentPlanResources =treatmentPlans.stream().map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.status(OK).body(treatmentPlanResources);
    }

    @Operation(summary = "Get all treatment plans by professional id", description = "Get all treatment plans with the given patient id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The treatment plans were found successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/professional/{id}")
    public ResponseEntity<List<TreatmentPlanResource>> getAllTreatmentPlanByProfessionalFullName(@PathVariable Long id) {
        var query = new GetAllTreatmentPlanByProfessionalIdQuery(id);
        List<TreatmentPlan> treatmentPlans = this.treatmentPlanQueryService.handle(query);

        if (treatmentPlans.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<TreatmentPlanResource> treatmentPlanResources =treatmentPlans.stream().map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.status(OK).body(treatmentPlanResources);
    }

}
