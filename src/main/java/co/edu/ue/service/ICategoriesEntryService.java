package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.entity.Category;
import co.edu.ue.entity.Entry;

public interface ICategoriesEntryService {

	List<CategoriesEntry> addCategoriesEntry(CategoriesEntry categoriesEntry);

	CategoriesEntry upCategoriesEntry(CategoriesEntry categoriesEntry);

	CategoriesEntry findByIdCategoriesEntry(int id);

	List<CategoriesEntry> listAllCategoriesEntry();

	Boolean existsCatEntId(int catEntId);

	Boolean existsBycategoryAndEntry(Category catId, Entry entId);

}
