package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.repository.dao.ICategoriesEntryRepositoryDao;


@Service
public class CategoriesEntryService implements ICategoriesEntryService {

	@Autowired
	ICategoriesEntryRepositoryDao categoriesEntryDAO;

	@Override
	public List<CategoriesEntry> addCategoriesEntry(CategoriesEntry categoriesEntry) {
		return categoriesEntryDAO.insertCategoriesEntry(categoriesEntry);
	}

	@Override
	public CategoriesEntry upCategoriesEntry(CategoriesEntry categoriesEntry) {
		return categoriesEntryDAO.updateCategoriesEntry(categoriesEntry);
	}

	@Override
	public CategoriesEntry findByIdCategoriesEntry(int id) {
		return categoriesEntryDAO.findIdCategoriesEntry(id);
	}

	@Override
	public List<CategoriesEntry> listAllCategoriesEntry() {
		return categoriesEntryDAO.listCategoriesEntry();
	}

	@Override
	public Boolean existsCatEntId(int catEntId) {
		return categoriesEntryDAO.existsCatEntId(catEntId);
	}

}
