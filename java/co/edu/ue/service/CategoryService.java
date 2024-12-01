package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Category;
import co.edu.ue.repository.dao.ICategoryRepositoryDao;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	ICategoryRepositoryDao categoryRepository;
	@Override
	public List<Category> addCategory(Category category) {
		
		return categoryRepository.insertCategory(category);
	}

	@Override
	public Category upCategory(Category category) {
		return categoryRepository.updateCategory(category);
	}

	@Override
	public Category findByIdCategory(int id) {
		return categoryRepository.findIdCategory(id);
	}

	@Override
	public List<Category> listAllCategories() {
		return categoryRepository.listCategory();
	}

	@Override
	public Boolean existsBycatId(int useId) {
		// TODO Auto-generated method stub
		return categoryRepository.existscatId(useId);
	}

}
