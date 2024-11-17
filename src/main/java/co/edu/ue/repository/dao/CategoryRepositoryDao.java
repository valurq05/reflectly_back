package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Category;
import co.edu.ue.repository.jpa.ICategory;
@Repository
public class CategoryRepositoryDao implements ICategoryRepositoryDao{

	@Autowired
	ICategory categoryJPA;

	@Override
	public List<Category> insertCategory(Category category) {
		categoryJPA.save(category);
		return listCategory();
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryJPA.save(category);
	}

	@Override
	public Category findIdCategory(int id) {
		return categoryJPA.findById(id).orElse(null);
	}

	@Override
	public List<Category> listCategory() {
		return categoryJPA.findAll();
	}

}
