package co.edu.ue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = {"co.edu.ue.entity"})
@EnableJpaRepositories(basePackages = {"co.edu.ue.repository.jpa"})
@ComponentScan(basePackages = {"co.edu.ue.repository.dao", "co.edu.ue.controller", "co.edu.ue.service", "co.edu.ue", "co.edu.ue.security"})
public class ProyectReflectlyApplication {

	    private static final Logger logger = LoggerFactory.getLogger(ProyectReflectlyApplication.class);
	public static void main(String[] args) {
		logger.info("GOOGLE_CLIENT_ID: " + System.getenv("GOOGLE_CLIENT_ID"));
        logger.info("GOOGLE_CLIENT_SECRET: " + System.getenv("GOOGLE_CLIENT_SECRET"));
		SpringApplication.run(ProyectReflectlyApplication.class, args);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encryptedPassword = "$2a$10$py0Y9X2WVau9j0/VIZ194.R8xwafUNk1SNtHb/vg9TnuBKYdt3t8q";
	    String rawPassword = "canelita";
	    
	    boolean matches = encoder.matches(rawPassword, encryptedPassword);
	    System.out.println("¿Coinciden las contraseñas? " + matches);
	}
}
