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



@RestController
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	ICategoryService CategoryService;
	
	@GetMapping(value="categories", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getAllCategorys(){
		
		return new ResponseEntity<List<Category>>(CategoryService.listAllCategories(), HttpStatus.OK);
	}
	
	@GetMapping(value="category", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategory(@RequestParam int id){
		
		return new ResponseEntity<Category>(CategoryService.findByIdCategory(id), HttpStatus.OK);
	}
	
	@PostMapping(value="category", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> postCategory(@RequestBody Category Category){
		
		return new ResponseEntity<List<Category>>(CategoryService.addCategory(Category), HttpStatus.OK);
	}
	
	@PutMapping(value="category", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> putCategory(@RequestBody Category Category){
		
		return new ResponseEntity<Category>(CategoryService.upCategory(Category), HttpStatus.OK);
	}
}
