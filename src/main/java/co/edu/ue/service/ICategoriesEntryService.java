package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.CategoriesEntry;


public interface ICategoriesEntryService {

	List<CategoriesEntry> addCategoriesEntry(CategoriesEntry categoriesEntry);
	CategoriesEntry upCategoriesEntry(CategoriesEntry categoriesEntry);
	CategoriesEntry findByIdCategoriesEntry(int id);
	List<CategoriesEntry> listAllCategoriesEntry();
	Boolean existsCatEntId(int catEntId);
}
