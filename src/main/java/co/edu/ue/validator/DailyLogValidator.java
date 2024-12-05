package co.edu.ue.validator;

import co.edu.ue.entity.DailyLog;
import co.edu.ue.service.IEmotionalLogService;
import co.edu.ue.service.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DailyLogValidator implements Validator {

    @Autowired
    private IEmotionalLogService emoLogService;

    @Autowired
    private IEntryService entryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return DailyLog.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DailyLog dailyLog = (DailyLog) target;

        if (dailyLog.getDayLogDate() == null) {
            errors.rejectValue("dayLogDate", "date.null", "La fecha no puede ser nula.");
        }


        if (dailyLog.getEmotionalLog() == null) {
            errors.rejectValue("emotionalLog", "emoLog.null", "El registro emocional no puede ser nulo.");
        } else {
            if (emoLogService.findByIdEmotionalLog(dailyLog.getEmotionalLog().getEmoLogId()) == null) {
                errors.rejectValue("emotionalLog", "emoLog.invalid", "El registro emocional no existe.");
            }
        }


        if (dailyLog.getEntry() == null) {
            errors.rejectValue("entry", "entry.null", "La entrada no puede ser nula.");
        } else {
            if (entryService.findByIdEntry(dailyLog.getEntry().getEntId()) == null) {
                errors.rejectValue("entry", "entry.invalid", "La entrada no existe.");
            }
        }
    }
}
