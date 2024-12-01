package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.Category;


public interface ICategoryService {

	List<Category> addCategory(Category category);
	Category upCategory(Category category);
	Category findByIdCategory(int id);
	List<Category> listAllCategories();
}
