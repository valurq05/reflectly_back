package co.edu.ue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.service.ICategoriesEntryService;
import co.edu.ue.service.ICategoryService;
import co.edu.ue.service.IEntryService;

@Component
public class CategoriesEntryValidator implements Validator {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IEntryService entryService;

    @Autowired
    private ICategoriesEntryService categoriesEntryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriesEntry.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoriesEntry categoriesEntry = (CategoriesEntry) target;

      
        if (categoriesEntry.getCategory() == null || categoriesEntry.getCategory().getCatId() <= 0) {
            errors.rejectValue("category", "Categoría inválida", "El ID de la categoría debe ser un número positivo.");
        } else if (!categoryService.existsBycatId(categoriesEntry.getCategory().getCatId())) {
            errors.rejectValue("category", "Categoría no encontrada", "No existe una categoría con el ID proporcionado.");
        }

 
        if (categoriesEntry.getEntry() == null || categoriesEntry.getEntry().getEntId() <= 0) {
            errors.rejectValue("entry", "Entrada inválida", "El ID de la entrada debe ser un número positivo.");
        } else if (!entryService.existsByentId(categoriesEntry.getEntry().getEntId())) {
            errors.rejectValue("entry", "Entrada no encontrada", "No existe una entrada con el ID proporcionado.");
        }

        if (categoriesEntry.getCategory() != null && categoriesEntry.getEntry() != null ) {
            boolean categoryExistsForEntry = categoriesEntryService.existsBycategoryAndEntry(
                categoryService.findByIdCategory(categoriesEntry.getCategory().getCatId()), entryService.findByIdEntry(categoriesEntry.getEntry().getEntId())
            );
            if (categoryExistsForEntry) {
                errors.rejectValue("category", "Categoría duplicada", "Esta categoría ya ha sido asignada a esta entrada.");
            }
        }
    }
}
