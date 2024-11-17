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

import co.edu.ue.entity.EmotionalLog;
import co.edu.ue.service.IEmotionalLogService;

@RestController
@CrossOrigin("*")
public class EmotionalLogController {

	@Autowired
	IEmotionalLogService EmotionalLogService;
	
	@GetMapping(value="Emotional/Logs", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmotionalLog>> getAllEmotionalLogs(){
		
		return new ResponseEntity<List<EmotionalLog>>(EmotionalLogService.listAllEmotionalLogs(), HttpStatus.OK);
	}
	
	@GetMapping(value="Emotional/Log", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmotionalLog> getEmotionalLog(@RequestParam int id){
		
		return new ResponseEntity<EmotionalLog>(EmotionalLogService.findByIdEmotionalLog(id), HttpStatus.OK);
	}
	
	@PostMapping(value="Emotional/Log", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmotionalLog>> postEmotionalLog(@RequestBody EmotionalLog EmotionalLog){
		
		return new ResponseEntity<List<EmotionalLog>>(EmotionalLogService.addEmotionalLog(EmotionalLog), HttpStatus.OK);
	}
	
	@PutMapping(value="Emotional/Log", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmotionalLog> putEmotionalLog(@RequestBody EmotionalLog EmotionalLog){
		
		return new ResponseEntity<EmotionalLog>(EmotionalLogService.upEmotionalLog(EmotionalLog), HttpStatus.OK);
	}
}
