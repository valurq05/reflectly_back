package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Person;

import co.edu.ue.entity.User;
import co.edu.ue.entity.UserRole;
import co.edu.ue.service.IPersonService;
import co.edu.ue.service.IUserRoleService;
import co.edu.ue.service.IUserService;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	IUserService userService;
	@Autowired
	IPersonService personService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	IUserRoleService userRoleService;
	
	
	@GetMapping(value="users", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.listAllUser(), HttpStatus.OK);
	}
	@GetMapping(value="user", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@RequestParam int id) {
		return new ResponseEntity<User>(userService.findByIdUser(id), HttpStatus.OK);
	}
	
	@PostMapping(value="register", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> postUser(@RequestBody User user){
		 Person person = user.getPerson();
		    personService.addPerson(person);
		    
	
		    Person lastPerson = personService.findByIdPerson(person.getPerId());
		    user.setPerson(lastPerson); 
		    
		    String encryptedPassword = passwordEncoder.encode(user.getUsePassword());
	        user.setUsePassword(encryptedPassword);
		    userService.addUser(user);
		    User lastUser = userService.findByIdUser(user.getUseId());
		    System.out.println(lastUser);
		    UserRole newUserRole = new UserRole();
		    newUserRole.setUseId(lastUser.getUseId());
		    newUserRole.setRolId(1);
		    
		    userRoleService.addUserRole(newUserRole);
		    
		    
	return new ResponseEntity<List<User>>(userService.listAllUser(), HttpStatus.OK);
	}
	
	
}
