package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.entity.Category;
import co.edu.ue.entity.Entry;
import co.edu.ue.repository.jpa.ICategoriesEntry;

@Repository
public class CategoriesEntryRepositoryDao implements ICategoriesEntryRepositoryDao {

	@Autowired
	ICategoriesEntry categoriesEntryJPA;

	@Override
	public List<CategoriesEntry> insertCategoriesEntry(CategoriesEntry categoriesEntry) {
		categoriesEntryJPA.save(categoriesEntry);
		return listCategoriesEntry();
	}

	@Override
	public CategoriesEntry updateCategoriesEntry(CategoriesEntry categoriesEntry) {

		return categoriesEntryJPA.save(categoriesEntry);
	}

	@Override
	public CategoriesEntry findIdCategoriesEntry(int id) {

		return categoriesEntryJPA.findById(id)
				.orElseThrow(() -> new RuntimeException("No se encontro la categoria asociada a la entrada"));
	}

	@Override
	public List<CategoriesEntry> listCategoriesEntry() {

		return categoriesEntryJPA.findAll();
	}

	@Override
	public Boolean existsCatEntId(int catEntId) {
		return categoriesEntryJPA.existsByCatEntIdAndCatEntStatusTrue(catEntId);
	}

	@Override
	public Boolean existsCategoryAndEntry(Category catId, Entry entId) {

		return categoriesEntryJPA.existsByCategoryAndEntryAndCatEntStatusTrue(catId, entId);
	}

	@Override
	public List<CategoriesEntry> findByEntry(Integer entId) {
		
		return categoriesEntryJPA.findAllByEntry(entId);
	}

	@Override
	public void toggleStatuseCatEntryId(Integer entryId) {
		categoriesEntryJPA.toggleStatusById(entryId);
	}

}
