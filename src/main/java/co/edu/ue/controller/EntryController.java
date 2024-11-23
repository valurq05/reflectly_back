package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Entry>> getAllEntries() {
        return new ResponseEntity<List<Entry>>(entryService.listAllEntry(), HttpStatus.OK);
    }

    @GetMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener una entrada por ID",
        description = "Devuelve la información de una entrada específica a partir de su ID.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<Entry> getEntry(@RequestParam int id) {
        return new ResponseEntity<Entry>(entryService.findByIdEntry(id), HttpStatus.OK);
    }

    @PostMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar una nueva entrada",
        description = "Permite registrar una nueva entrada en el sistema proporcionando la información correspondiente.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<List<Entry>> postEntry(@RequestBody Entry entry) {
        return new ResponseEntity<List<Entry>>(entryService.addEntry(entry), HttpStatus.OK);
    }

    @PutMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar una entrada",
        description = "Actualiza la información de una entrada existente en el sistema.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<Entry> putEntry(@RequestBody Entry entry) {
        return new ResponseEntity<Entry>(entryService.upEntry(entry), HttpStatus.OK);
    }
}
