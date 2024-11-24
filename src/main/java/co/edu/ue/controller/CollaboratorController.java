package co.edu.ue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.service.ICollaboratorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CollaboratorController {

    @Autowired
    ICollaboratorService collaboratorService;

    @GetMapping(value = "collaborators", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todos los colaboradores",
        description = "Devuelve una lista de todos los colaboradores registrados.",
        tags = {"Colaboradores"}
    )
    public ResponseEntity<?> getAllCollaborators() {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", collaboratorService.listAllCollaborators());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "collaborator", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener un colaborador por ID",
        description = "Devuelve la información de un colaborador específico a partir de su ID.",
        tags = {"Colaboradores"}
    )
    public ResponseEntity<?> getCollaborator(@RequestParam int id) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", collaboratorService.findByIdCollaborator(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "collaborator", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar un nuevo colaborador",
        description = "Permite registrar un colaborador proporcionando su información.",
        tags = {"Colaboradores"}
    )
    public ResponseEntity<?> postCollaborator(@RequestBody Collaborator collaborator) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", collaboratorService.addCollaborator(collaborator));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "collaborator", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar un colaborador",
        description = "Actualiza la información de un colaborador existente.",
        tags = {"Colaboradores"}
    )
    public ResponseEntity<?> putCollaborator(@RequestBody Collaborator collaborator) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", collaboratorService.upCollaborator(collaborator));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
