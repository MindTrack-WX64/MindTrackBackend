package com.mindtrack.backend.profiles.interfaces.rest;

import com.mindtrack.backend.profiles.domain.model.queries.GetPatientByUserIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetPatientsByProfessionalIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfessionalByUserIdQuery;
import com.mindtrack.backend.profiles.domain.services.PatientCommandService;
import com.mindtrack.backend.profiles.domain.services.PatientQueryService;
import com.mindtrack.backend.profiles.domain.services.ProfessionalQueryService;
import com.mindtrack.backend.profiles.interfaces.rest.resources.PatientResource;
import com.mindtrack.backend.profiles.interfaces.rest.resources.ProfessionalResource;
import com.mindtrack.backend.profiles.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import com.mindtrack.backend.profiles.interfaces.rest.transform.ProfessionalResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profiles Management Endpoints")
public class ProfilesController {
    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;
    private final ProfessionalQueryService professionalQueryService;

    public ProfilesController(PatientCommandService patientCommandService, PatientQueryService patientQueryService, ProfessionalQueryService professionalQueryService) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
        this.professionalQueryService = professionalQueryService;
    }

    @Operation(summary = "Get Patient by User Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    @GetMapping("/patients/{userId}")
    public ResponseEntity<PatientResource> getPatient(@PathVariable Long userId) {
        var query = new GetPatientByUserIdQuery(userId);
        var patient = this.patientQueryService.handle(query);
        if (patient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());

        return ResponseEntity.ok(patientResource);
    }

    @Operation(summary = "Get Professional by User Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professional found"),
            @ApiResponse(responseCode = "404", description = "Professional not found")
    })
    @GetMapping("/professionals/{userId}")
    public ResponseEntity<ProfessionalResource> getProfessional(@PathVariable Long userId) {
        var query = new GetProfessionalByUserIdQuery(userId);
        var professional = this.professionalQueryService.handle(query);
        if (professional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var professionalResource = ProfessionalResourceFromEntityAssembler.toResourceFromEntity(professional.get());

        return ResponseEntity.ok(professionalResource);
    }

    @Operation(summary = "Get patients by professional id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patients found"),
            @ApiResponse(responseCode = "404", description = "Patients not found")
    })
    @GetMapping("/professionals/{professionalId}/patients")
    public ResponseEntity<List<PatientResource>> getPatientsByProfessional(@PathVariable Long professionalId) {
        var query = new GetPatientsByProfessionalIdQuery(professionalId);
        var patients = this.patientQueryService.handle(query);
        if (patients.isEmpty()) return ResponseEntity.notFound().build();
        var patientsResource = patients.stream().map(PatientResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(patientsResource);
    }
}
