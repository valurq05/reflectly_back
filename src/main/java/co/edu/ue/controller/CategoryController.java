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

import co.edu.ue.entity.Category;
import co.edu.ue.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @GetMapping(value = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Listar todas las categorías",
        description = "Devuelve una lista de todas las categorías relacionadas con las entradas de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<List<Category>>(categoryService.listAllCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "category", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener una categoría por ID",
        description = "Devuelve la información de una categoría específica asociada con las entradas de diario, a partir de su ID.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<Category> getCategory(@RequestParam int id) {
        return new ResponseEntity<Category>(categoryService.findByIdCategory(id), HttpStatus.OK);
    }

    @PostMapping(value = "category", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar una nueva categoría",
        description = "Permite registrar una nueva categoría para clasificar entradas de diario proporcionando su información.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<List<Category>> postCategory(@RequestBody Category category) {
        return new ResponseEntity<List<Category>>(categoryService.addCategory(category), HttpStatus.OK);
    }

    @PutMapping(value = "category", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Actualizar una categoría",
        description = "Actualiza la información de una categoría existente que clasifica entradas de diario.",
        tags = {"Entradas de Diario"}
    )
    public ResponseEntity<Category> putCategory(@RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.upCategory(category), HttpStatus.OK);
    }
}
