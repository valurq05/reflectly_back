package co.edu.ue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.LogInRequest;
import co.edu.ue.entity.User;
import co.edu.ue.security.SecurityConfig;
import co.edu.ue.service.IUserService;
import co.edu.ue.validator.UserValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

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
	@Operation(
	    summary = "Inicio de sesión",
	    description = "Permite a los usuarios autenticarse proporcionando su correo electrónico y contraseña.",
	    tags = {"Usuarios"}
	)
	public ResponseEntity<?> login(@RequestBody LogInRequest logInInfo) {

	    String user = logInInfo.getUser();
	    String pwd = logInInfo.getPwd();
	    System.out.println(user);
	    System.out.println(pwd);
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
	        Map<String, Object> response = new HashMap<>();
	        response.put("Status", false);
	        response.put("Data", validationErrors);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    try {
	        Authentication authentication = authManager.authenticate(
	                new UsernamePasswordAuthenticationToken(user, pwd));
	        System.out.println("Controller Usuario autenticado: " + authentication.getName());
	        System.out.println("Controller Autoridades: " + authentication.getAuthorities());

	        String token = getToken(authentication);
	        String refreshToken = getRefreshTokenString(authentication);
	        
	        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
	        		.httpOnly(true)
	        		.secure(true)
	        		.path("/")
	        		.maxAge(7 * 24 * 60 * 60) 
	                .sameSite("Strict")
	                .build();
	        
	        User userLog = userService.findByMailUser(authentication.getName());

	        Map<String, Object> response = new HashMap<String, Object>();
	        response.put("Token", token);
	       
	        response.put("Data", userLog);
	        response.put("Status", true);
	        
	        HttpHeaders headers = new HttpHeaders();
	        
	        headers.add("Set-Cookie", refreshTokenCookie.toString());
	        
	        
	        return new ResponseEntity<>(response, headers,HttpStatus.OK);
	    } catch (AuthenticationException ex) {
	        ex.printStackTrace();
	        Map<String, Object> response = new HashMap<String, Object>();
	        response.put("Status", false);
	        response.put("Data", "Error de autenticación");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	}

	private String getToken(Authentication authentication) {

	    List<String> authorities = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());
 
	
	    System.out.println("*****************COntroller***********************");
	    System.out.println("Autoridades: " + authorities);
	    System.out.println("****************************************");
	    String token = Jwts.builder()
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setSubject(authentication.getName())
	        .claim("authorities", authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
	        .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
	        .compact();
	    return token;
	}
	
	private String getRefreshTokenString(Authentication authentication) {
		List<String> authorities = authentication.getAuthorities().stream()
		        .map(GrantedAuthority::getAuthority)
		        .collect(Collectors.toList());

		    String refreshToken = Jwts.builder()
		        .setIssuedAt(new Date(System.currentTimeMillis()))
		        .setSubject(authentication.getName())
		        .claim("authorities", authorities)
		        .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) 
		        .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
		        .compact();
		    return refreshToken;
	}
	
	@PostMapping("/refresh")
	@Operation(
		    summary = "Refrescar Token de acceso",
		    description = "Permite al servicio del cliente actualizar el token al haber expirado",
		    tags = {"Usuarios"}
		)
	public ResponseEntity<?> refreshAccessToken(@CookieValue @Parameter(hidden = true) String refreshToken){
		
		if (refreshToken == null || refreshToken.isEmpty()) {
	        return new ResponseEntity<>(Map.of("Status", false, "Data", "Refresh token no proporcionado"), HttpStatus.UNAUTHORIZED);
	    }
		
		try {
			
			  Claims claims = Jwts.parserBuilder()
		                .setSigningKey(CLAVE.getBytes())
		                .build()
		                .parseClaimsJws(refreshToken)
		                .getBody();
			  
			  String username = claims.getSubject();
		        User user = userService.findByMailUser(username);
		      
		        if (user == null) {
		            return new ResponseEntity<>(Map.of("Status", false, "Data", "Usuario no encontrado"), HttpStatus.UNAUTHORIZED);
		        }
			  
		        String newAccessToken = Jwts.builder()
		        	    .setIssuedAt(new Date())
		        	    .setSubject(claims.getSubject())
		        	    .claim("authorities", claims.get("authorities"))
		        	    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) 
		        	    .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
		        	    .compact();
			  
			  Map<String, Object> response = new HashMap<String, Object>();
			  response.put("Data", user);
			  response.put("Claims", claims);
			  response.put("ResfreshToken", refreshToken);
			  response.put("Status", true);
			  response.put("NewAccessToken", newAccessToken);
			  return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(JwtException e) {
			Map<String, Object> response = new HashMap<String, Object>();
	        response.put("Status", false);
	        response.put("ErrorMessage", e.getMessage());
	        response.put("Data", "Error de autenticación");
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

}






