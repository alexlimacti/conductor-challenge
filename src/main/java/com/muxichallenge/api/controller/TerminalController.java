package com.muxichallenge.api.controller;

import com.muxichallenge.api.dto.TerminalDTO;
import com.muxichallenge.domain.service.TerminalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @GetMapping
    @Operation(summary = "Find all terminals", tags = {"terminal"})
    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TerminalDTO.class))))
    public ResponseEntity<List<TerminalDTO>> getAllTerminals() {
        return ResponseEntity.ok().body(terminalService.findAll());
    }

    @GetMapping("/{logic}")
    @Operation(summary = "Find terminal by logic", tags = {"terminal"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = TerminalDTO.class))),
            @ApiResponse(responseCode = "404", description = "Terminal not found")
    })
    public ResponseEntity<TerminalDTO> getTerminal(@PathVariable Integer logic){
        return ResponseEntity.ok().body(terminalService.findByLogic(logic));
    }

    @PostMapping
    @Operation(summary = "Add a new terminal", description = "", tags = { "terminal" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "terminal created", content = @Content(schema = @Schema(implementation = TerminalDTO.class))),
            @ApiResponse(responseCode = "400", description = "required fields: logic, serial, model, version"),
            @ApiResponse(responseCode = "409", description = "terminal logic existing")
    })
    public ResponseEntity<TerminalDTO> saveTerminal(@Valid @RequestBody TerminalDTO terminalDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(terminalService.save(terminalDTO));
    }

    @PutMapping("/{logic}")
    @Operation(summary = "Update a terminal", description = "", tags = { "terminal" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "terminal updated", content = @Content(schema = @Schema(implementation = TerminalDTO.class))),
            @ApiResponse(responseCode = "400", description = "required fields: logic, serial, model, version"),
            @ApiResponse(responseCode = "404", description = "Terminal not found"),
            @ApiResponse(responseCode = "409", description = "terminal logic existing")
    })
    public ResponseEntity<?> updateTerminal(@PathVariable Integer logic,
                                 @RequestBody @Valid TerminalDTO terminalDTO) {
        return ResponseEntity.ok().body(terminalService.update(logic, terminalDTO));
    }

    @PatchMapping("/{logic}")
    @Operation(summary = "Partial update a terminal", description = "", tags = { "terminal" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "terminal updated", content = @Content(schema = @Schema(implementation = TerminalDTO.class))),
            @ApiResponse(responseCode = "404", description = "Terminal not found"),
            @ApiResponse(responseCode = "409", description = "terminal logic existing")
    })
    public ResponseEntity<?> updatePartialTerminal(@PathVariable Integer logic,
                                                   @RequestBody Map<String, Object> fields, HttpServletRequest request) {
        return ResponseEntity.ok().body(terminalService.partialUpdate(logic, fields, request));
    }

}
