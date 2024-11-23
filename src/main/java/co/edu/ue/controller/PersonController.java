package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Person;
import co.edu.ue.service.IPersonService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
public class PersonController {
	@Autowired
	IPersonService personService;
	
	@GetMapping(value="persons", produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(
	    summary = "Listar todas las personas",
	    description = "Devuelve una lista con toda la información de las personas registradas en el sistema.",
	    tags = {"Usuarios"}
	)
	public ResponseEntity<List<Person>> getAllPersons() {
	    return new ResponseEntity<List<Person>>(personService.listAllPersons(), HttpStatus.OK);
	}

	@GetMapping(value="person", produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(
	    summary = "Obtener una persona por ID",
	    description = "Devuelve la información de una persona específica a partir de su ID.",
	    tags = {"Usuarios"}
	)
	public ResponseEntity<Person> getPerson(@RequestParam int id) {
	    return new ResponseEntity<Person>(personService.findByIdPerson(id), HttpStatus.OK);
	}


}
