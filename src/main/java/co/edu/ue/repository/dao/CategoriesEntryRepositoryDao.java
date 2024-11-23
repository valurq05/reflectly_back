package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.repository.jpa.ICategoriesEntry;

@Repository
public class CategoriesEntryRepositoryDao implements ICategoriesEntryRepositoryDao{

	@Autowired
	ICategoriesEntry categoriesEntryJPA;
	@Override
	public List<CategoriesEntry> insertCategoriesEntry(CategoriesEntry categoriesEntry) {
		categoriesEntryJPA.save(categoriesEntry);
		return listCategoriesEntry();
	}

	@Override
	public CategoriesEntry updateCategoriesEntry(CategoriesEntry categoriesEntry) {
		// TODO Auto-generated method stub
		return categoriesEntryJPA.save(categoriesEntry);
	}

	@Override
	public CategoriesEntry findIdCategoriesEntry(int id) {
		// TODO Auto-generated method stub
		return categoriesEntryJPA.findById(id).orElse(null);
	}

	@Override
	public List<CategoriesEntry> listCategoriesEntry() {
		// TODO Auto-generated method stub
		return categoriesEntryJPA.findAll();
	}

}
