package co.edu.ue.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


 
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    

		try {
		http.csrf(cus->cus.disable())
		.authorizeHttpRequests(aut->
			aut.requestMatchers(HttpMethod.GET,"/users").authenticated()			
			.requestMatchers(HttpMethod.GET,"/user").authenticated()	
			.requestMatchers(HttpMethod.POST,"/user").permitAll()	
			.requestMatchers(HttpMethod.GET,"/persons").permitAll()
			.requestMatchers(HttpMethod.GET,"/categories").permitAll()
			.requestMatchers(HttpMethod.GET,"/category").permitAll()
			.requestMatchers(HttpMethod.POST,"/category").permitAll()
			.requestMatchers(HttpMethod.PUT,"/category").permitAll()
			.requestMatchers(HttpMethod.GET,"/emotional/states").permitAll()
			.requestMatchers(HttpMethod.GET,"/emotional/state").permitAll()
			.requestMatchers(HttpMethod.POST,"/emotional/state").permitAll()
			.requestMatchers(HttpMethod.PUT,"/emotional/state").permitAll()
			.requestMatchers(HttpMethod.GET,"/entries").permitAll()
			.requestMatchers(HttpMethod.GET,"/entry").permitAll()
			.requestMatchers(HttpMethod.POST,"/entry").permitAll()
			.requestMatchers(HttpMethod.PUT,"/entry").permitAll()
			.requestMatchers(HttpMethod.GET,"/images").permitAll()
			.requestMatchers(HttpMethod.GET,"/image").permitAll()
			.requestMatchers(HttpMethod.POST,"/image").permitAll()
			.requestMatchers(HttpMethod.PUT,"/image").permitAll()
			.requestMatchers(HttpMethod.GET,"/collaborators").permitAll()
			.requestMatchers(HttpMethod.GET,"/collaborator").permitAll()
			.requestMatchers(HttpMethod.POST,"/collaborator").permitAll()
			.requestMatchers(HttpMethod.PUT,"/collaborator").permitAll()
			.requestMatchers(HttpMethod.GET,"/Emotional/Logs").permitAll()
			.requestMatchers(HttpMethod.GET,"/Emotional/Logs").permitAll()
			.requestMatchers(HttpMethod.POST,"/Emotiona/lLogs").permitAll()
			.requestMatchers(HttpMethod.PUT,"/Emotional/Logs").permitAll()
			.requestMatchers(
	                "/swagger-ui/**",
	                "/v3/api-docs/**",
	                "/swagger-resources/**",
	                "/webjars/**"
	            ).permitAll()
	            .anyRequest().authenticated()
			
			
			).httpBasic(Customizer.withDefaults());
		
		}catch (Exception e) {
				e.printStackTrace();
				}
		return http.build();	
	}
}
