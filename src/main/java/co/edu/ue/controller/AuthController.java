package co.edu.ue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.User;
import co.edu.ue.security.SecurityConfig;
import co.edu.ue.service.IUserService;
import co.edu.ue.validator.UserValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static co.edu.ue.utils.Tools.*;

@RestController
@CrossOrigin("*")
public class AuthController {
	AuthenticationManager authManager;
	
	@Autowired
	IUserService userService;
	@Autowired
	UserValidator userValidator;
	
	 
	public AuthController(AuthenticationManager authManager) {
		super();
		this.authManager = authManager;
		
	}
	@PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestParam("user") String user,
	                                     @RequestParam("pwd") String pwd) {
		
		User validate = new User();
		validate.setUseMail(user);
		validate.setUsePassword(pwd);
		
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(validate, "user");
		
		userValidator.validateLogIn(validate, result);
		
		
		
		if (result.hasErrors()) {
       
            Map<String, String> validationErrors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage()
                    ));
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }
	    try {
	    	
	        Authentication authentication = authManager.authenticate(
	                new UsernamePasswordAuthenticationToken(user, pwd));
	        System.out.println("COntroller Usuario autenticado: " + authentication.getName());
	        System.out.println("COntroller Autoridades: " + authentication.getAuthorities());
	        
	        String token = getToken(authentication);
	        
	        User userLog = userService.findByMailUser(authentication.getName());
	       
	        Map<String, Object> response = new HashMap<String, Object>();
	        response.put("Token", token);
	        response.put("User", userLog);
	        
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (AuthenticationException ex) {
	        ex.printStackTrace();
	        return new ResponseEntity<>("Error de autenticación: " + ex.getMessage(),HttpStatus.UNAUTHORIZED);
	    }
	}
	
	private String getToken(Authentication authentication) {
		// Asegúrate de que estás obteniendo las autoridades correctas
	    List<String> authorities = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());
 
	    // Imprimir las autoridades para depuración
	    System.out.println("*****************COntroller***********************");
	    System.out.println("Autoridades: " + authorities);
	    System.out.println("****************************************");
	    String token = Jwts.builder()
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setSubject(authentication.getName())
	        .claim("authorities", authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
	        .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
	        .compact();
	    return token;
	}
}
