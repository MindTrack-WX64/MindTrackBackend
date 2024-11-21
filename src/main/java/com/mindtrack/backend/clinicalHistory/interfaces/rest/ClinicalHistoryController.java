package com.mindtrack.backend.clinicalHistory.interfaces.rest;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetAllClinicalHistoryQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetClinicalHistoryByIdQuery;
import com.mindtrack.backend.clinicalHistory.domain.model.queries.GetClinicalHistoryByPatientIdQuery;
import com.mindtrack.backend.clinicalHistory.domain.services.ClinicalHistoryCommandService;
import com.mindtrack.backend.clinicalHistory.domain.services.ClinicalHistoryQueryService;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.AddSymptomResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.ClinicalHistoryResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.CreateClinicalHistoryResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.resources.UpdateClinicalHistoryResource;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.AddSymptomCommandFromResourceAssembler;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.ClinicalHistoryResourceFromEntityAssembler;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.CreateClinicalHistoryCommandFromResourceAssembler;
import com.mindtrack.backend.clinicalHistory.interfaces.rest.transform.UpdateClinicalHistoryCommandFromResourceAssembler;
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
@RequestMapping(value ="/api/v1/clinicalHistories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clinical History", description = "The Clinical History Controller")
public class ClinicalHistoryController {
    private final ClinicalHistoryCommandService clinicalHistoryCommandService;
    private final ClinicalHistoryQueryService clinicalHistoryQueryService;

    public ClinicalHistoryController(ClinicalHistoryCommandService clinicalHistoryCommandService, ClinicalHistoryQueryService clinicalHistoryQueryService) {
        this.clinicalHistoryCommandService = clinicalHistoryCommandService;
        this.clinicalHistoryQueryService = clinicalHistoryQueryService;
    }

    @Operation(summary = "Create a new clinical history", description = "Create a new clinical history with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The clinical history was created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping
    public ResponseEntity<ClinicalHistoryResource> createClinicalHistory(@RequestBody CreateClinicalHistoryResource clinicalHistoryResource) {
        Optional<ClinicalHistory> clinicalHistory = this.clinicalHistoryCommandService.handle(CreateClinicalHistoryCommandFromResourceAssembler.toCommandFromResource(clinicalHistoryResource));
        return clinicalHistory.map(ClinicalHistoryResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(CREATED).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a clinical history", description = "Update a clinical history with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The clinical history was updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClinicalHistoryResource> updateClinicalHistory(@PathVariable Long id, @RequestBody UpdateClinicalHistoryResource resource) {
        Optional<ClinicalHistory> clinicalHistory = this.clinicalHistoryCommandService.handle(UpdateClinicalHistoryCommandFromResourceAssembler.toCommandFromResource(resource, id));
        return clinicalHistory.map(source -> ResponseEntity.ok(ClinicalHistoryResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add a new symptom to a clinical history", description = "Add a new symptom to a clinical history with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The symptom was added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PutMapping("/symptoms")
    public ResponseEntity<ClinicalHistoryResource> addSymptom(@RequestBody AddSymptomResource resource) {
        Optional<ClinicalHistory> clinicalHistory = this.clinicalHistoryCommandService.handle(AddSymptomCommandFromResourceAssembler.toCommandFromResource(resource));
        return clinicalHistory.map(source -> ResponseEntity.ok(ClinicalHistoryResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all clinical histories", description = "Get all clinical histories")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The clinical histories were retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping
    public ResponseEntity<List<ClinicalHistoryResource>> getAllClinicalHistories() {
        var query = new GetAllClinicalHistoryQuery();
        List<ClinicalHistory> clinicalHistories = this.clinicalHistoryQueryService.handle(query);

        if (clinicalHistories.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ClinicalHistoryResource> resources = clinicalHistories.stream().map(ClinicalHistoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get a clinical history by id", description = "Get a clinical history by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The clinical history was retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClinicalHistoryResource> getClinicalHistoryById(@PathVariable Long id) {
        var query = new GetClinicalHistoryByIdQuery(id);
        Optional<ClinicalHistory> clinicalHistory = this.clinicalHistoryQueryService.handle(query);

        return clinicalHistory.map(p -> new ResponseEntity<>(ClinicalHistoryResourceFromEntityAssembler.toResourceFromEntity(p), OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all clinical histories by patient id", description = "Get all clinical histories by patient id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The clinical histories were retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<ClinicalHistoryResource>> getClinicalHistoriesByPatientEmail(@PathVariable Long id) {
        var query = new GetClinicalHistoryByPatientIdQuery(id);
        Optional<ClinicalHistory> clinicalHistories = this.clinicalHistoryQueryService.handle(query);

        if (clinicalHistories.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ClinicalHistoryResource> resources = clinicalHistories.stream().map(ClinicalHistoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }
}
