package co.edu.ue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.service.ICollaboratorService;
import co.edu.ue.service.IEntryService;
import co.edu.ue.service.IUserService;

@Component
public class CollaboratorValidator implements Validator {

    @Autowired
    private IEntryService entryService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICollaboratorService collaboratorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Collaborator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Collaborator collaborator = (Collaborator) target;

        // Validar Entry
        if (collaborator.getEntry() == null || collaborator.getEntry().getEntId() <= 0) {
            errors.rejectValue("entry", "entry.invalid", "El ID de la entrada es inválido");
        } else if (!entryService.existsByentId(collaborator.getEntry().getEntId())) {
            errors.rejectValue("entry", "entry.notFound", "No existe una entrada con el ID proporcionado");
        }

        
        if (collaborator.getUser() == null || collaborator.getUser().getUseId() <= 0) {
            errors.rejectValue("user", "user.invalid", "El ID de usuario es inválido");
        } else if (!userService.existByIdUser(collaborator.getUser().getUseId())) {
            errors.rejectValue("user", "user.notFound", "No existe un usuario con el ID proporcionado");
        }

        if (collaboratorService.existsByUserAndEntry( collaborator.getUser(), collaborator.getEntry())) {
            errors.rejectValue("user", "user.alreadyAssociated", "El usuario ya está asociado a esta entrada");
        }
    }
}
