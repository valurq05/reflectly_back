package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.entity.Category;
import co.edu.ue.entity.Entry;

public interface ICategoriesEntryRepositoryDao {
	List<CategoriesEntry> insertCategoriesEntry(CategoriesEntry categoriesEntry);

	CategoriesEntry updateCategoriesEntry(CategoriesEntry categoriesEntry);

	CategoriesEntry findIdCategoriesEntry(int id);

	List<CategoriesEntry> listCategoriesEntry();

	Boolean existsCatEntId(int catEntId);

	Boolean existsCategoryAndEntry(Category catId, Entry entId);

	List<CategoriesEntry> findByEntry( Integer entId);

	void toggleStatuseCatEntryId(Integer entryId);


}
