package com.mindtrack.backend.session.interfaces.rest;

import com.mindtrack.backend.session.domain.model.aggregates.Session;
import com.mindtrack.backend.session.domain.model.entities.Note;
import com.mindtrack.backend.session.domain.model.queries.*;
import com.mindtrack.backend.session.domain.services.SessionCommandService;
import com.mindtrack.backend.session.domain.services.SessionQueryService;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateNoteResource;
import com.mindtrack.backend.session.interfaces.rest.resources.CreateSessionResource;
import com.mindtrack.backend.session.interfaces.rest.resources.NoteResource;
import com.mindtrack.backend.session.interfaces.rest.resources.SessionResource;
import com.mindtrack.backend.session.interfaces.rest.transform.CreateNoteCommandFromResourceAssembler;
import com.mindtrack.backend.session.interfaces.rest.transform.CreateSessionCommandFromResourceAssembler;
import com.mindtrack.backend.session.interfaces.rest.transform.NoteResourceFromEntityAssembler;
import com.mindtrack.backend.session.interfaces.rest.transform.SessionResourceFromEntityAssembler;
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
@RequestMapping(value ="/api/v1/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Session", description = "The Session Controller")
public class SessionController {
    private final SessionCommandService sessionCommandService;
    private final SessionQueryService sessionQueryService;

    public SessionController(SessionCommandService sessionCommandService, SessionQueryService sessionQueryService) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
    }

    @Operation(summary = "Create a new session", description = "Create a new session with the given data")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The session was created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping
    public ResponseEntity<SessionResource> createSession(@RequestBody CreateSessionResource sessionResource) {
        Optional<Session> session = this.sessionCommandService.handle(CreateSessionCommandFromResourceAssembler.toCommandFromResource(sessionResource));
        return session.map(SessionResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(CREATED).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all sessions", description = "Get all sessions")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The sessions were retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping
    public ResponseEntity<List<SessionResource>> getAllSessions() {
        var query = new GetAllSessionQuery();
        List<Session> sessions = this.sessionQueryService.handle(query);

        if (sessions.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SessionResource> resources = sessions.stream().map(SessionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get a session by id", description = "Get a session by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The session was retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SessionResource> getSessionById(@PathVariable Long id) {
        var query = new GetSessionByIdQuery(id);
        Optional<Session> session = this.sessionQueryService.handle(query);

        return session.map(SessionResourceFromEntityAssembler::toResourceFromEntity)
                .map(resource -> ResponseEntity.status(OK).body(resource))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get session by Professional Id", description = "Get session by Professional Id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The session was retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/professional/{id}")
    public ResponseEntity<List<SessionResource>> getSessionByProfessionalEmail(@PathVariable Long id) {
        var query = new GetAllProfessionalSessionsQuery(id);
        List<Session> sessions = this.sessionQueryService.handle(query);

        if (sessions.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SessionResource> resources = sessions.stream().map(SessionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get session by Session Date", description = "Get session by Session Date")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The session was retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/date/{date}")
    public ResponseEntity<List<SessionResource>> getSessionByDate(@PathVariable LocalDate date) {
        var query = new GetAllSessionBySessionDateQuery(date);
        List<Session> sessions = this.sessionQueryService.handle(query);

        if (sessions.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SessionResource> resources = sessions.stream().map(SessionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Add notes to session", description = "Add notes to session")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The notes were added to the session successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @PostMapping("/notes")
    public ResponseEntity<SessionResource> addNotesToSession(@RequestBody CreateNoteResource resource) {
        Optional<Session> session = this.sessionCommandService.handle(CreateNoteCommandFromResourceAssembler.toCommandFromResource(resource));
        return session.map(source -> ResponseEntity.ok(SessionResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get all notes by session id", description = "Get all notes by session id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The notes were retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
    })
    @GetMapping("/notes/{sessionId}")
    public ResponseEntity<List<NoteResource>> getAllNotesBySessionId(@PathVariable Long sessionId) {
        var query = new GetAllNotesBySessionIdQuery(sessionId);
        List<Note> notes = this.sessionQueryService.handle(query);

        if (notes.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<NoteResource> resources = notes.stream().map(NoteResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }
}
