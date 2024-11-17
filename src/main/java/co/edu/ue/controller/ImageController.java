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

import co.edu.ue.entity.Image;
import co.edu.ue.service.IImageService;

@RestController
@CrossOrigin("*")
public class ImageController {

	@Autowired
	IImageService imageService;
	
	@GetMapping(value="images", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Image>> getAllImages(){
		
		return new ResponseEntity<List<Image>>(imageService.listAllImages(), HttpStatus.OK);
		
	}
	
	@GetMapping(value="image", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Image> getImage(@RequestParam int id){
		
		return new ResponseEntity<Image>(imageService.findByIdImage(id), HttpStatus.OK);
		
	}
	
	@PostMapping(value="image", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Image>> postImage(@RequestBody Image image){
		
		return new ResponseEntity<List<Image>>(imageService.addImage(image), HttpStatus.OK);
		
	}
	
	@PutMapping(value="image", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Image> getImage(@RequestBody Image image){
		
		return new ResponseEntity<Image>(imageService.upImage(image), HttpStatus.OK);
		
	}
}