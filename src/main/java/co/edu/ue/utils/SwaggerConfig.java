package co.edu.ue.utils;

import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
		info=@Info(
				title="Reflectly",
				description = "Reflectly API te permite gestionar entradas de diario, analizar estados de ánimo"
								+ " y compartir pensamientos. Incluye autenticación JWT para garantizar la seguridad de los datos.",
				contact = @Contact(name = "Sebastian Hernandez",
									email = "reflectly@gmail.com"),
				termsOfService = "Todos los derechos reservados, feo el que robe.",
				version = "0.1.1"
				
				),
		security = @SecurityRequirement(
				name="Security TokenJWT"
				),
		tags = {
				@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios."),
		        @Tag(name = "Entradas de Diario", description = "CRUD de entradas de diario."),
		        @Tag(name = "Análisis de Estado de Ánimo", description = "Funciones para analizar los estados de ánimo del usuario."),
		        @Tag(name = "Colaboradores", description = "Operaciones relacionadas con la gestión de colaboradores"),
		    }
		
		)

@SecurityScheme(
		name = "Security TokenJWT",
		description = "La seguridad del api es por medio de un token JWT",
		type = SecuritySchemeType.HTTP,
		paramName = HttpHeaders.AUTHORIZATION,
		in = SecuritySchemeIn.HEADER,
		scheme = "bearer", bearerFormat = "JWT"
		)
public class SwaggerConfig {

}
