package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.Category;



public interface ICategoryRepositoryDao {
	
	List<Category> insertCategory(Category category);
	Category updateCategory(Category category);
	Category findIdCategory(int id);
	List<Category> listCategory();
	Boolean existscatId(int useId);


}
