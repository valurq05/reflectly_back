package co.edu.ue.validator;

import co.edu.ue.entity.Person;
import co.edu.ue.entity.User;
import co.edu.ue.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator{

	 
    private static final String EMAIL_REGEX = "[\\w]+@[\\w]{2,}+\\.[\\w]{2,}";
    private static final int MIN_PASSWORD_LENGTH = 8; 
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%,._;:*#?&])[A-Za-z\\d@$!%.,;:_*#?&]{8,20}$";
    private static final String NAMES_LASTNAMES_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ s]{2,30}+$";

    @Autowired
    IUserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

    
        if (user.getUseMail() == null || user.getUseMail().isEmpty()) {
            errors.rejectValue("useMail", "Email vacío", "El correo electrónico no puede estar vacío");
        } else if (!Pattern.matches(EMAIL_REGEX, user.getUseMail())) {
            errors.rejectValue("useMail", "Email inválido", "El correo electrónico no tiene un formato válido");
        } else if(userService.existByMailUser(user.getUseMail())) {
        	errors.rejectValue("useMail", "Email duplicado", "El correo electrónico ya está registrado");
        }

      
        if (user.getUsePassword() == null || user.getUsePassword().isEmpty()) {
            errors.rejectValue("usePassword", "Contraseña vacía", "La contraseña no puede estar vacía");
        } else if (user.getUsePassword().length() < MIN_PASSWORD_LENGTH || user.getUsePassword().length() > MAX_PASSWORD_LENGTH || !Pattern.matches(PASSWORD_REGEX, user.getUsePassword())) {
            errors.rejectValue("usePassword", "Contraseña inválida", "La contraseña debe tener entre 8 y 20 caracteres, contener una letra, un numero y un caracter especial (@$!%*,._;:#?&)");
        }

     
        Person person = user.getPerson();
        if (person == null) {
            errors.rejectValue("person", "Persona vacía", "Los datos de la persona no pueden estar vacíos");
            return;
        }

    
        if (person.getPerDocument() == null || person.getPerDocument().isEmpty()) {
            errors.rejectValue("person.perDocument", "Documento vacío", "El documento no puede estar vacío");
        } else if (!person.getPerDocument().matches("^[0-9]{8,15}$")) {
            errors.rejectValue("person.perDocument", "Documento inválido", "El documento debe contener entre 8 y 15 dígitos");
        }

    
        if (person.getPerLastname() == null || person.getPerLastname().isEmpty()) {
            errors.rejectValue("person.perLastname", "Apellidos vacíos", "Los apellidos no pueden estar vacíos");
        } else if (!person.getPerLastname().matches(NAMES_LASTNAMES_REGEX)) {
            errors.rejectValue("person.perLastname", "Apellidos inválidos", "Los apellidos solo pueden contener letras y espacios");
        }

 
        if (person.getPerName() == null || person.getPerName().isEmpty()) {
            errors.rejectValue("person.perName", "Nombre vacío", "El nombre no puede estar vacío");
        } else if (!person.getPerName().matches(NAMES_LASTNAMES_REGEX)) {
            errors.rejectValue("person.perName", "Nombre inválido", "El nombre solo puede contener letras y espacios");
        }

        
        if (person.getPerPhoto() == null || person.getPerPhoto().isEmpty()) {
            errors.rejectValue("person.perPhoto", "Foto vacía", "La foto no puede estar vacía");
        }
    }
    
    public void validateLogIn(Object target, Errors errors) {
    	User user = (User) target;

        
        if (user.getUseMail() == null || user.getUseMail().isEmpty()) {
            errors.rejectValue("useMail", "Email vacío", "El correo electrónico no puede estar vacío");
        } else if (!Pattern.matches(EMAIL_REGEX, user.getUseMail())) {
            errors.rejectValue("useMail", "Email inválido", "El correo electrónico no tiene un formato válido");
        }

      
        if (user.getUsePassword() == null || user.getUsePassword().isEmpty()) {
            errors.rejectValue("usePassword", "Contraseña vacía", "La contraseña no puede estar vacía");
        } else if (user.getUsePassword().length() < MIN_PASSWORD_LENGTH || user.getUsePassword().length() > MAX_PASSWORD_LENGTH || !Pattern.matches(PASSWORD_REGEX, user.getUsePassword())) {
            errors.rejectValue("usePassword", "Contraseña inválida", "La contraseña debe tener entre 8 y 20 caracteres, contener una letra, un numero y un caracter especial (@$!%*,._;:#?&)");
        }
    	
    }

	
}
