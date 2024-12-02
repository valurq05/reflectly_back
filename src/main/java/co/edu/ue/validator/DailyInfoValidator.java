package co.edu.ue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.edu.ue.dto.DailyLogFullInfo;
import co.edu.ue.service.IEmotionalStateService;
import co.edu.ue.service.IUserService;

@Component
public class DailyInfoValidator implements Validator {

	 private static final int MAX_TITLE_LENGTH = 100;
	    private static final int MAX_TEXT_LENGTH = 1000;

	    @Autowired
	    private IUserService userService;

	    @Autowired
	    private IEmotionalStateService emotionalStateService;

	    @Override
	    public boolean supports(Class<?> clazz) {
	        return DailyLogFullInfo.class.equals(clazz);
	    }

	    @Override
	    public void validate(Object target, Errors errors) {
	        DailyLogFullInfo dailyLog = (DailyLogFullInfo) target;

	        
	        if (dailyLog.getUseId() <= 0) {
	            errors.rejectValue("useId", "ID de usuario inválido", "El ID del usuario debe ser un número positivo");
	        } else if (!userService.existByIdUser(dailyLog.getUseId())) {
	            errors.rejectValue("useId", "Usuario no encontrado", "No existe un usuario con el ID proporcionado");
	        }

	        
	        if (dailyLog.getEmoStaId() <= 0) {
	            errors.rejectValue("emoStaId", "ID de estado emocional inválido", "El ID del estado emocional debe ser un número positivo");
	        } else if (!emotionalStateService.existsByEmoStaId(dailyLog.getEmoStaId())) {
	            errors.rejectValue("emoStaId", "Estado emocional no encontrado", "No existe un estado emocional con el ID proporcionado");
	        }

	        
	        if (dailyLog.getEntTitle() == null || dailyLog.getEntTitle().isEmpty()) {
	            errors.rejectValue("entTitle", "Título vacío", "El título no puede estar vacío");
	        } else if (dailyLog.getEntTitle().length() > MAX_TITLE_LENGTH) {
	            errors.rejectValue("entTitle", "Título demasiado largo", "El título no puede tener más de " + MAX_TITLE_LENGTH + " caracteres");
	        }

	      
	        if (dailyLog.getEntText() == null || dailyLog.getEntText().isEmpty()) {
	            errors.rejectValue("entText", "Texto vacío", "El texto de la entrada no puede estar vacío");
	        } else if (dailyLog.getEntText().length() > MAX_TEXT_LENGTH) {
	            errors.rejectValue("entText", "Texto demasiado largo", "El texto de la entrada no puede tener más de " + MAX_TEXT_LENGTH + " caracteres");
	        }
	    }
	
}
