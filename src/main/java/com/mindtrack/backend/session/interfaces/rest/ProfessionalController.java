package com.mindtrack.backend.session.interfaces.rest;

import com.mindtrack.backend.session.domain.model.entities.Professional;
import com.mindtrack.backend.session.domain.model.queries.GetAllProfessionalQuery;
import com.mindtrack.backend.session.domain.model.queries.GetProfessionalByIdQuery;
import com.mindtrack.backend.session.domain.services.ProfessionalCommandService;
import com.mindtrack.backend.session.domain.services.ProfessionalQueryService;
import com.mindtrack.backend.session.interfaces.rest.resources.AddPatientResource;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateProfessionalResource;
import com.mindtrack.backend.session.interfaces.rest.resources.ProfessionalResource;
import com.mindtrack.backend.session.interfaces.rest.transform.AddPatientCommandFromResourceAssembler;
import com.mindtrack.backend.session.interfaces.rest.transform.CreateProfessionalCommandFromResourceAssembler;
import com.mindtrack.backend.session.interfaces.rest.transform.ProfessionalResourceFromEntityAssembler;
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
@RequestMapping(value ="/api/v1/professionals", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Professional", description = "The Professional Controller")
public class ProfessionalController {
    private final ProfessionalCommandService professionalCommandService;
    private final ProfessionalQueryService professionalQueryService;

    public ProfessionalController(ProfessionalCommandService professionalCommandService, ProfessionalQueryService professionalQueryService) {
        this.professionalCommandService = professionalCommandService;
        this.professionalQueryService = professionalQueryService;
    }

    @Operation(summary = "Create a new professional", description = "Create a new professional with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The professional was created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping
    public ResponseEntity<ProfessionalResource> createProfessional(@RequestBody CreateProfessionalResource professionalResource) {
        Optional<Professional> professional = this.professionalCommandService.handle(CreateProfessionalCommandFromResourceAssembler.toCommandFromResource(professionalResource));
        return professional.map(ProfessionalResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(CREATED).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all professionals", description = "Get all professionals")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The professionals were retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping
    public ResponseEntity<List<ProfessionalResource>> getAllProfessionals() {
        var query = new GetAllProfessionalQuery();
        List<Professional> professionals = this.professionalQueryService.handle(query);

        if (professionals.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ProfessionalResource> professionalResources = professionals.stream().map(ProfessionalResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(professionalResources);
    }

    @Operation(summary = "Get a professional by id", description = "Get a professional by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The professional was retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResource> getProfessionalById(@PathVariable Long id) {
        var query = new GetProfessionalByIdQuery(id);
        Optional<Professional> professional = this.professionalQueryService.handle(query);

        return professional.map(ProfessionalResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Add Patient to Professional", description = "Add Patient to Professional")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The patient was added to the professional successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping("/patients")
    public ResponseEntity<ProfessionalResource> addPatientToProfessional(@RequestBody AddPatientResource resource) {
        Optional<Professional> professional = this.professionalCommandService.handle(AddPatientCommandFromResourceAssembler.toCommandFromResource(resource));
        return professional.map(source -> ResponseEntity.ok(ProfessionalResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
