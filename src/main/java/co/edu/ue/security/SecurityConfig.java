package co.edu.ue.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


 
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	AuthenticationManager auth;

    @Bean
    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	@Bean	
	public AuthenticationManager authManager(AuthenticationConfiguration conf) {
		try {
			
			auth= conf.getAuthenticationManager();
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
			e.printStackTrace();
		}
		return auth;
	}
	
	@Bean
	public JdbcUserDetailsManager usersDetailsJdbc() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/reflectly");
		ds.setUsername("root");
		ds.setPassword("root");
		
		JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);
		
		  jdbcDetails.setUsersByUsernameQuery(
		            "SELECT use_mail, use_password, use_status FROM users WHERE use_mail = ? AND use_status = 1"
		        );
			
		 jdbcDetails.setAuthoritiesByUsernameQuery(
			        "SELECT u.use_mail, r.rol_name " +
			        "FROM user_roles ur " +
			        "JOIN users u ON ur.use_id = u.use_id " +
			        "JOIN roles r ON ur.rol_id = r.rol_id " +
			        "WHERE u.use_mail = ?"
			    );

	
		printUsers(ds);	
		return jdbcDetails;
	}
	
	
	private void printUsers(DriverManagerDataSource ds) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
 
        // Imprimir usuarios
        List<String> users = jdbcTemplate.query("SELECT use_mail FROM users", (rs, rowNum) -> rs.getString("use_mail"));
        System.out.println("Usuarios en la base de datos: " + users);
 
        // Imprimir roles (asumiendo que deseas imprimir todos los roles)
        List<String> roles = jdbcTemplate.query("SELECT rol_name FROM roles", (rs, rowNum) -> rs.getString("rol_name"));
        System.out.println("Roles en la base de datos: " + roles);
        
        
    }
	

    // Configuración CORS
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // Permitir solicitudes desde el frontend
        config.addAllowedHeader("*"); // Permitir todos los headers
        config.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
        config.setAllowCredentials(true); // Permitir cookies o credenciales si es necesario

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica configuración CORS a todas las rutas
        return source;
    }



	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    


		http
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.csrf(cus->cus.disable())
		.authorizeHttpRequests(aut->
			aut.requestMatchers(HttpMethod.GET,"/users").hasAnyRole("ADMIN","USER")			
			.requestMatchers(HttpMethod.GET,"/user").hasAnyRole("ADMIN","USER")	
			.requestMatchers(HttpMethod.POST,"/register").permitAll()
			.requestMatchers(HttpMethod.POST,"/login").permitAll()
			.requestMatchers(HttpMethod.GET,"/persons").permitAll()
			.requestMatchers(HttpMethod.GET,"/categories").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/category").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/category").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/category").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/emotional/states").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/emotional/state").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/emotional/state").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/emotional/state").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/entries").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/entry").permitAll()
			.requestMatchers(HttpMethod.POST,"/entry").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/entry").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/images").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/image").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/image").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/image").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/collaborators").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/collaborator").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/collaborator").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/collaborator").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/Emotional/Logs").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/Emotional/Logs").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/Emotiona/Logs").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/Emotional/Logs").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/categories/entries").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/categories/entry").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/categories/entry").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/categories/entry").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/daily/logs").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.GET,"/daily/log").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.POST,"/daily/log").hasAnyRole("ADMIN","USER")
			.requestMatchers(HttpMethod.PUT,"/daily/log").hasAnyRole("ADMIN","USER")
			.requestMatchers(
	                "/swagger-ui/**",
	                "/v3/api-docs/**",
	                "/swagger-resources/**",
	                "/webjars/**"
	            ).permitAll()
	            .anyRequest().authenticated()
			
			
			).addFilter(new AuthorizationFilterJWT(auth));
		return http.build();	
	}
}
