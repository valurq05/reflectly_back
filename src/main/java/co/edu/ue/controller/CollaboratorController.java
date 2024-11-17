package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.service.ICollaboratorService;

public class CollaboratorController {

	@Autowired
	ICollaboratorService collaboratorService;
	
	@GetMapping(value="collaborators", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Collaborator>> getAllCollaborators(){
		
		return new ResponseEntity<List<Collaborator>>(collaboratorService.listAllCollaborators(), HttpStatus.OK);
	}
	
	@GetMapping(value="collaborator", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collaborator> getCollaborator(@RequestParam int id){
		
		return new ResponseEntity<Collaborator>(collaboratorService.findByIdCollaborator(id), HttpStatus.OK);
	}
	
	@PostMapping(value="collaborator", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Collaborator>> postCollaborator(@RequestBody Collaborator Collaborator){
		
		return new ResponseEntity<List<Collaborator>>(collaboratorService.addCollaborator(Collaborator), HttpStatus.OK);
	}
	
	@PutMapping(value="collaborator", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collaborator> putCollaborator(@RequestBody Collaborator Collaborator){
		
		return new ResponseEntity<Collaborator>(collaboratorService.upCollaborator(Collaborator), HttpStatus.OK);
	}
}
