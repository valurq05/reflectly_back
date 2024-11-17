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

@RestController
@CrossOrigin("*")
public class EntryController {
	@Autowired
	IEntryService entryService;

	@GetMapping(value = "entries", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Entry>> getAllEntries() {

		return new ResponseEntity<List<Entry>>(entryService.listAllEntry(), HttpStatus.OK);
	}
	
	@GetMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Entry> getEntry(@RequestParam int id) {

		return new ResponseEntity<Entry>(entryService.findByIdEntry(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Entry>> postEntry(@RequestBody Entry entry) {

		return new ResponseEntity<List<Entry>>(entryService.addEntry(entry), HttpStatus.OK);
	}
	
	@PutMapping(value = "entry", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Entry> putEntry(@RequestBody Entry entry) {

		return new ResponseEntity<Entry>(entryService.upEntry(entry), HttpStatus.OK);
	}
}
