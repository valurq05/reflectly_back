package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.CategoriesEntry;



public interface ICategoriesEntryRepositoryDao {
	List<CategoriesEntry> insertCategoriesEntry(CategoriesEntry categoriesEntry);
	CategoriesEntry updateCategoriesEntry(CategoriesEntry categoriesEntry);
	CategoriesEntry findIdCategoriesEntry(int id);
	List<CategoriesEntry> listCategoriesEntry();
}
