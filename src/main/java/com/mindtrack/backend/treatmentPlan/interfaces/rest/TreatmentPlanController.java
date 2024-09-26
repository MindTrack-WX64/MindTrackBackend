package com.mindtrack.backend.treatmentPlan.interfaces.rest;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetAllTreatmentPlanByPatientFullNameQuery;
import com.mindtrack.backend.treatmentPlan.domain.model.queries.GetAllTreatmentPlanByProfessionalFullNameQuery;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @PutMapping("/biological-function")
    public ResponseEntity<TreatmentPlanResource> addBiologicalFunction(@RequestBody AddBiologicalFunctionResource biologicalFunctionResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(AddBiologicalFunctionsCommandFromResourceAssembler.toCommandFromResource(biologicalFunctionResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add diagnostic to treatment plan", description = "Add a diagnostic to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The diagnostic was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/diagnostic")
    public ResponseEntity<TreatmentPlanResource> addDiagnostic(@RequestBody AddDiagnosticResource diagnosticResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(AddDiagnosticCommandFromResourceAssembler.toCommandFromResource(diagnosticResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add patient state to treatment plan", description = "Add a patient state to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The patient state was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/patient-state")
    public ResponseEntity<TreatmentPlanResource> addPatientState(@RequestBody AddPatientStateResource patientStateResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(AddPatientStateCommandFromResourceAssembler.toCommandFromResource(patientStateResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add prescription to treatment plan", description = "Add a prescription to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The prescription was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/prescription")
    public ResponseEntity<TreatmentPlanResource> addPrescription(@RequestBody AddPrescriptionResource prescriptionResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(AddPrescriptionCommandFromResourceAssembler.toCommandFromResource(prescriptionResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add session to treatment plan", description = "Add a session to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The session was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/session")
    public ResponseEntity<TreatmentPlanResource> addSession(@RequestBody AddSessionResource sessionResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(AddSessionCommandFromResourceAssembler.toCommandFromResource(sessionResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add task to treatment plan", description = "Add a task to the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The task was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/task")
    public ResponseEntity<TreatmentPlanResource> addTask(@RequestBody AddTaskResource taskResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(AddTaskCommandFromResourceAssembler.toCommandFromResource(taskResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Start task in treatment plan", description = "Start a task in the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The task was started successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/task/start")
    public ResponseEntity<TreatmentPlanResource> startTask(@RequestBody StartTaskResource startTaskResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(StartTaskCommandFromResourceAssembler.toCommandFromResource(startTaskResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Complete task in treatment plan", description = "Complete a task in the treatment plan with the given id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The task was completed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/task/complete")
    public ResponseEntity<TreatmentPlanResource> completeTask(@RequestBody FinishTaskResource completeTaskResource) {
        Optional<TreatmentPlan> treatmentPlan = this.treatmentPlanCommandService.handle(FinishTaskCommandFromResourceAssembler.toCommandFromResource(completeTaskResource));
        return treatmentPlan.map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
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

    @Operation(summary = "Get all treatment plans by patient full name", description = "Get all treatment plans with the given patient full name")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The treatment plans were found successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/patient/{fullName}")
    public ResponseEntity<List<TreatmentPlanResource>> getAllTreatmentPlanByPatientFullName(@PathVariable String fullName) {
        var query = new GetAllTreatmentPlanByPatientFullNameQuery(fullName);
        List<TreatmentPlan> treatmentPlans = this.treatmentPlanQueryService.handle(query);

        if (treatmentPlans.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<TreatmentPlanResource> treatmentPlanResources =treatmentPlans.stream().map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.status(OK).body(treatmentPlanResources);
    }

    @Operation(summary = "Get all treatment plans by professional full name", description = "Get all treatment plans with the given patient full name")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The treatment plans were found successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/professional/{fullName}")
    public ResponseEntity<List<TreatmentPlanResource>> getAllTreatmentPlanByProfessionalFullName(@PathVariable String fullName) {
        var query = new GetAllTreatmentPlanByProfessionalFullNameQuery(fullName);
        List<TreatmentPlan> treatmentPlans = this.treatmentPlanQueryService.handle(query);

        if (treatmentPlans.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<TreatmentPlanResource> treatmentPlanResources =treatmentPlans.stream().map(TreatmentPlanResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.status(OK).body(treatmentPlanResources);
    }

    @Operation(summary = "Get treatment plan biological functions statistics", description = "Get the statistics of the biological functions of the treatment plans")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The statistics were found successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/biological-functions/statistics/{treatmentPlanId}")
    public ResponseEntity<List<TreatmentPlanStatistics>> getTreatmentPlanBiologicalFunctionsStatistics(@PathVariable Long treatmentPlanId) {
        var query = new GetTreatmentPlanStatisticsDataQuery(treatmentPlanId);
        List<TreatmentPlanStatistics> treatmentPlanStatistics = this.treatmentPlanQueryService.handle(query);

        if (treatmentPlanStatistics.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(OK).body(treatmentPlanStatistics);
    }

}
