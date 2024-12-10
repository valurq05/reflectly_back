package co.edu.ue.controller;


import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.entity.Entry;
import co.edu.ue.service.ICategoriesEntryService;
import co.edu.ue.validator.CategoriesEntryValidator;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class CategoriesEntryController {
	@Autowired
    ICategoriesEntryService categoriesEntryService;
    @Autowired
    CategoriesEntryValidator categoriesEntryValidator;

    @GetMapping(value = "categories/entries", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todas las categorías en entradas",
        description = "Devuelve una lista de todas las categorías relacionadas con una o varias entradas de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> getAllCategories() {
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", categoriesEntryService.listAllCategoriesEntry());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener una categoría de una entrada por ID",
        description = "Devuelve la información de una categoría específica asociada con una entrada de diario, a partir de su ID.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> getCategoriesEntry(@RequestParam int id) {
    	Map<String, Object> response = new HashMap<>();
        if (id <= 0) {
            response.put("Status", false);
            response.put("Errors", Map.of("id", "El ID debe ser un número positivo"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        CategoriesEntry categoriesEntry = categoriesEntryService.findByIdCategoriesEntry(id);
        if (categoriesEntry == null) {
            response.put("Status", false);
            response.put("Errors", Map.of("id", "No existe una categoría con el ID proporcionado"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("Status", true);
        response.put("Data", categoriesEntryService.findByIdCategoriesEntry(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar una nueva categoría para una entrada",
        description = "Permite añadir una nueva categoría a entradas de diario para clasificarlas.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> postCategoriesEntry(@RequestBody CategoriesEntry CategoriesEntry, BindingResult result) {

        categoriesEntryValidator.validate(CategoriesEntry, result);

        if (result.hasErrors()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", false);
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            response.put("Errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", categoriesEntryService.addCategoriesEntry(CategoriesEntry));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar una categoría de una entrada",
        description = "Actualiza la asociación de una categoria con la entrada de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> putCategoriesEntry(@RequestBody CategoriesEntry CategoriesEntry, BindingResult result) {
        categoriesEntryValidator.validate(CategoriesEntry, result);

        if (result.hasErrors()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", false);
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            response.put("Errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", categoriesEntryService.upCategoriesEntry(CategoriesEntry));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "Categories/by/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar una categoría de una entrada",
        description = "Actualiza la asociación de una categoria con la entrada de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> getCategoriesEntryByeEntity(@RequestParam Integer entry) {
        
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", categoriesEntryService.findByEntry(entry));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Desactivar una categoría de una entrada",
        description = "Desactiva una categoría de una entrada de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<?> toggleCategoryEntryStatus(@RequestParam Integer catEntId) {
        categoriesEntryService.toggleStatuseCatEntryId(catEntId);
    	Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", "Estado de la categoría de la entrada actualizado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
