package com.mindtrack.backend.clinicalHistory.interfaces.rest;

import com.mindtrack.backend.clinicalHistory.domain.model.entities.Patient;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetAllPatientQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetPatientByIdQuery;
import com.mindtrack.backend.clinicalHistory.domain.services.PatientCommandService;
import com.mindtrack.backend.clinicalHistory.domain.services.PatientQueryService;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.CreatePatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.CreatePatientCommandFromResourceAssembler;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value ="/api/v1/patients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Patient", description = "The Patients Controller")
public class PatientsController {
    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;

    public PatientsController(PatientCommandService patientCommandService, PatientQueryService patientQueryService) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
    }

    @Operation(summary = "Create a new patient", description = "Create a new patient with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The patient was created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource patientResource) {
        Optional<Patient> patient = this.patientCommandService.handle(CreatePatientCommandFromResourceAssembler.toCommandFromResource(patientResource));
        return patient.map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(CREATED).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all patients", description = "Get all patients")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The patients were retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping
    public ResponseEntity<List<PatientResource>> getAllPatients() {
        var query = new GetAllPatientQuery();
        List<Patient> patients = this.patientQueryService.handle(query);

        if (patients.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<PatientResource> resources = patients.stream().map(PatientResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get a patient by id", description = "Get a patient by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The patient was retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientResource> getPatientById(@PathVariable Long id) {
        var query = new GetPatientByIdQuery(id);
        Optional<Patient> patient = this.patientQueryService.handle(query);

        return patient.map(p -> new ResponseEntity<>(PatientResourceFromEntityAssembler.toResourceFromEntity(p), OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
