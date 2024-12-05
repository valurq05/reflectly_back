package co.edu.ue.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.edu.ue.entity.Category;

@Component
public class CategoryValidator implements Validator {

    private static final int MAX_CATEGORY_NAME_LENGTH = 100;

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;

        // Validar el nombre de la categoría
        if (category.getCatCategorie() == null || category.getCatCategorie().isEmpty()) {
            errors.rejectValue("catCategorie", "Nombre vacío", "El nombre de la categoría no puede estar vacío");
        } else if (category.getCatCategorie().length() > MAX_CATEGORY_NAME_LENGTH) {
            errors.rejectValue("catCategorie", "Nombre demasiado largo",
                    "El nombre de la categoría no puede exceder " + MAX_CATEGORY_NAME_LENGTH + " caracteres");
        }
    }
}
