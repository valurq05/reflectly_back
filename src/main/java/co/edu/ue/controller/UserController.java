package co.edu.ue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista con todos los usuarios registrados en el sistema.", tags = {
            "Usuarios" })
    public ResponseEntity<?> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("Data", userService.listAllUser());
        response.put("Status", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve un usuario específico basado en su identificador único (ID).", tags = {
            "Usuarios" })
    public ResponseEntity<?> getUser(@RequestParam int id) {
        Map<String, Object> response = new HashMap<>();
        response.put("Data", userService.findByIdUser(id));
        response.put("Status", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "user/mail", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener un usuario por correo electrónico", description = "Devuelve un usuario específico basado en su correo electrónico.", tags = {
            "Usuarios" })
    public ResponseEntity<?> getUserByMail(@RequestParam String mail) {

        Map<String, Object> response = new HashMap<>();
        response.put("Data", userService.findByMailUser(mail));
        response.put("Status", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar un nuevo usuario", description = "Permite a los usuarios registrarse con sus datos personales y credenciales de usuario.", tags = {
            "Usuarios" })
    public ResponseEntity<?> postUser(@RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage()));
            Map<String, Object> response = new HashMap<>();
            response.put("Data", errors);
            response.put("Status", false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", userService.addUser(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "register/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Asignar rol de administrador a un usuario existente o registrar un nuevo administrador", description = "Si el usuario ya existe, se le asigna el rol de administrador. Si no existe, se registra como nuevo administrador.", tags = {
            "Usuarios" })
    public ResponseEntity<?> postUserAdmin(@RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);

        if (userService.existByMailUser(user.getUseMail())) {
            User userFound = userService.findByMailUser(user.getUseMail());

            List<UserRole> roles = userRoleService.findByUseId(userFound.getUseId());
            boolean isAdmin = roles.stream().anyMatch(role -> role.getRolId() == 1);

            if (isAdmin) {
                Map<String, Object> response = new HashMap<>();
                response.put("Status", false);
                response.put("Data", "El usuario ya tiene el rol de administrador");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            UserRole newRole = new UserRole();
            newRole.setUseId(userFound.getUseId());
            newRole.setRolId(1);
            userRoleService.addUserRole(newRole);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Data", "Rol de administrador asignado al usuario existente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage()));
            Map<String, Object> response = new HashMap<>();
            response.put("Data", errors);
            response.put("Status", false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Status", true);
        response.put("Data", userService.addUserAdmin(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "user/exist", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Saber si un usuario ya está registrado con un correo electrónico", description = "Devuelve true o false si un usuario ya está registrado con un correo electrónico.", tags = {
            "Usuarios" })
    public ResponseEntity<Boolean> getUserExistence(@RequestParam String email) {

        return new ResponseEntity<Boolean>(userService.existByMailUser(email), HttpStatus.OK);
    }

}
