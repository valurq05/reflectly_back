package co.edu.ue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Entry;
import co.edu.ue.service.IEntryService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class EntryController {

    @Autowired
    IEntryService entryService;

    @GetMapping(value = "entries", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todas las entradas",
        description = "Devuelve una lista con todas las entradas registradas en el sistema.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> getAllEntries() {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", entryService.listAllEntry());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener una entrada por ID",
        description = "Devuelve la información de una entrada específica a partir de su ID.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> getEntry(@RequestParam int id) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", entryService.findByIdEntry(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar una nueva entrada",
        description = "Permite registrar una nueva entrada en el sistema proporcionando la información correspondiente.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> postEntry(@RequestBody Entry entry) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", entryService.addEntry(entry));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar una entrada",
        description = "Actualiza la información de una entrada existente en el sistema.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> putEntry(@RequestBody Entry entry) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", entryService.upEntry(entry));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
  

}
