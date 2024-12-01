package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the categories_entries database table.
 * 
 */
@Entity
@Table(name="categories_entries")
@NamedQuery(name="CategoriesEntry.findAll", query="SELECT c FROM CategoriesEntry c")
public class CategoriesEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_ent_id")
	private int catEntId;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="cat_id")
	private Category category;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	@JoinColumn(name="ent_id")
	private Entry entry;

	public CategoriesEntry() {
	}

	public int getCatEntId() {
		return this.catEntId;
	}

	public void setCatEntId(int catEntId) {
		this.catEntId = catEntId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}