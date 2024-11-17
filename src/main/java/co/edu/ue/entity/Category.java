package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_id")
	private int catId;

	@Column(name="cat_categorie")
	private String catCategorie;

//	//bi-directional many-to-one association to CategoriesEntry
//	@OneToMany(mappedBy="category")
//	private List<CategoriesEntry> categoriesEntries;

	public Category() {
	}

	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatCategorie() {
		return this.catCategorie;
	}

	public void setCatCategorie(String catCategorie) {
		this.catCategorie = catCategorie;
	}

//	public List<CategoriesEntry> getCategoriesEntries() {
//		return this.categoriesEntries;
//	}
//
//	public void setCategoriesEntries(List<CategoriesEntry> categoriesEntries) {
//		this.categoriesEntries = categoriesEntries;
//	}
//
//	public CategoriesEntry addCategoriesEntry(CategoriesEntry categoriesEntry) {
//		getCategoriesEntries().add(categoriesEntry);
//		categoriesEntry.setCategory(this);
//
//		return categoriesEntry;
//	}
//
//	public CategoriesEntry removeCategoriesEntry(CategoriesEntry categoriesEntry) {
//		getCategoriesEntries().remove(categoriesEntry);
//		categoriesEntry.setCategory(null);
//
//		return categoriesEntry;
//	}

}