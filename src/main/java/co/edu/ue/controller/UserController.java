package co.edu.ue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
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
import co.edu.ue.validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;

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
    @Autowired
    UserValidator userValidator;

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Devuelve una lista con todos los usuarios registrados en el sistema.",
        tags = {"Usuarios"}
    )
    public ResponseEntity<?> getAllUsers() {
    	Map<String, Object> response = new HashMap<>();
        response.put("Data", userService.listAllUser());
        response.put("Status", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener un usuario por ID",
        description = "Devuelve un usuario específico basado en su identificador único (ID).",
        tags = {"Usuarios"}
    )
    public ResponseEntity<?> getUser(@RequestParam int id) {
    	Map<String, Object> response = new HashMap<>();
        response.put("Data", userService.findByIdUser(id));
        response.put("Status", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "user/mail", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obtener un usuario por correo electrónico",
        description = "Devuelve un usuario específico basado en su correo electrónico.",
        tags = {"Usuarios"}
    )
    public ResponseEntity<?> getUserByMail(@RequestParam String mail) {
    	
    	Map<String, Object> response = new HashMap<>();
        response.put("Data", userService.findByMailUser(mail));
        response.put("Status", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Registrar un nuevo usuario",
        description = "Permite a los usuarios registrarse con sus datos personales y credenciales de usuario.",
        tags = {"Usuarios"}
    )
    public ResponseEntity<?> postUser(@RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                .collect(Collectors.toMap(
                    error -> error.getField(),
                    error -> error.getDefaultMessage()
                ));
            Map<String, Object> response = new HashMap<>();
            response.put("Data", errors);
            response.put("Status", false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Person person = user.getPerson();
        personService.addPerson(person);
        String defaultImagePath = "/images/GdXyg8gWgAAQmW1.jpg";
        person.setPerPhoto(defaultImagePath);
        Person lastPerson = personService.findByIdPerson(person.getPerId());
        user.setPerson(lastPerson);

        String encryptedPassword = passwordEncoder.encode(user.getUsePassword());
        user.setUsePassword(encryptedPassword);
        userService.addUser(user);
        User lastUser = userService.findByIdUser(user.getUseId());
        System.out.println(lastUser);
        UserRole newUserRole = new UserRole();
        newUserRole.setUseId(lastUser.getUseId());
        newUserRole.setRolId(2);
        userRoleService.addUserRole(newUserRole);
        

        Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", lastUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
