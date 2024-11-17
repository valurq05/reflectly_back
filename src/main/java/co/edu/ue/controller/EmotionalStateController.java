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

import co.edu.ue.entity.EmotionalState;
import co.edu.ue.service.IEmotionalStateService;

@RestController
@CrossOrigin("*")
public class EmotionalStateController {

	@Autowired
	IEmotionalStateService emoStateService;
	
	@GetMapping(value="emotional/states", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmotionalState>> getAllEmotionalStates(){
		
		return new ResponseEntity<List<EmotionalState>>(emoStateService.listAllEmotionalState(), HttpStatus.OK);
	}
	
	@GetMapping(value="emotional/state", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmotionalState> getEmotionalState(@RequestParam int id){
		
		return new ResponseEntity<EmotionalState>(emoStateService.findByIdEmotionalState(id), HttpStatus.OK);
	}
	
	@PostMapping(value="emotional/state", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmotionalState>> postEmotionalState(@RequestBody EmotionalState emotionalState){
		
		return new ResponseEntity<List<EmotionalState>>(emoStateService.addEmotionalState(emotionalState), HttpStatus.OK);
	}
	
	@PutMapping(value="emotional/state", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmotionalState> putEmotionalState(@RequestBody EmotionalState emotionalState){
		
		return new ResponseEntity<EmotionalState>(emoStateService.upEmotionalState(emotionalState), HttpStatus.OK);
	}
}
