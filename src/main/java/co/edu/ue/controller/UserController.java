package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Person;
import co.edu.ue.entity.User;
import co.edu.ue.service.IPersonService;
import co.edu.ue.service.IUserService;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	IUserService userService;
	@Autowired
	IPersonService personService;
	
	
	@GetMapping(value="users", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.listAllUser(), HttpStatus.OK);
	}
	@GetMapping(value="user", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@RequestParam int id) {
		return new ResponseEntity<User>(userService.findByIdUser(id), HttpStatus.OK);
	}
	
	@PostMapping(value="user", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> postUser(@RequestBody User user){
		 Person person = user.getPerson();
		    personService.addPerson(person);
		    
	
		    Person lastPerson = personService.findByIdPerson(person.getPerId());
		    user.setPerson(lastPerson); 
		    

		    userService.addUser(user);
	return new ResponseEntity<List<User>>(userService.listAllUser(), HttpStatus.OK);
	}
	
	
}
