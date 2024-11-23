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

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.service.ICategoriesEntryService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class CategoriesEntryController {
	@Autowired
    ICategoriesEntryService categoriesEntryService;

    @GetMapping(value = "categories/entries", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todas las categorías en entradas",
        description = "Devuelve una lista de todas las categorías relacionadas con una o varias entradas de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<List<CategoriesEntry>> getAllCategories() {
        return new ResponseEntity<List<CategoriesEntry>>(categoriesEntryService.listAllCategoriesEntry(), HttpStatus.OK);
    }

    @GetMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener una categoría de una entrada por ID",
        description = "Devuelve la información de una categoría específica asociada con una entrada de diario, a partir de su ID.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<CategoriesEntry> getCategoriesEntry(@RequestParam int id) {
        return new ResponseEntity<CategoriesEntry>(categoriesEntryService.findByIdCategoriesEntry(id), HttpStatus.OK);
    }

    @PostMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar una nueva categoría para una entrada",
        description = "Permite añadir una nueva categoría a entradas de diario para clasificarlas.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<List<CategoriesEntry>> postCategoriesEntry(@RequestBody CategoriesEntry CategoriesEntry) {
        return new ResponseEntity<List<CategoriesEntry>>(categoriesEntryService.addCategoriesEntry(CategoriesEntry), HttpStatus.OK);
    }

    @PutMapping(value = "Categories/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar una categoría de una entrada",
        description = "Actualiza la asociación de una categoria con la entrada de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<CategoriesEntry> putCategoriesEntry(@RequestBody CategoriesEntry CategoriesEntry) {
        return new ResponseEntity<CategoriesEntry>(categoriesEntryService.upCategoriesEntry(CategoriesEntry), HttpStatus.OK);
    }
}
