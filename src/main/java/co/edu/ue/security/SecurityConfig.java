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
			.requestMatchers(HttpMethod.POST,"/user").authenticated()		
			
			).httpBasic(Customizer.withDefaults());
		
		}catch (Exception e) {
				e.printStackTrace();
				}
		return http.build();	
	}
}
