package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the entries database table.
 * 
 */
@Entity
@Table(name="entries")
@NamedQuery(name="Entry.findAll", query="SELECT e FROM Entry e")
public class Entry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ent_id")
	private int entId;

	@Temporal(TemporalType.DATE)
	@Column(name="ent_date")
	private Date entDate;

	@Lob
	@Column(name="ent_text")
	private String entText;

	@Column(name="ent_title")
	private String entTitle;

//	//bi-directional many-to-one association to CategoriesEntry
//	@OneToMany(mappedBy="entry")
//	private List<CategoriesEntry> categoriesEntries;
//
//	//bi-directional many-to-one association to Collaborator
//	@OneToMany(mappedBy="entry")
//	private List<Collaborator> collaborators;
//
//	//bi-directional many-to-one association to DailyLog
//	@OneToMany(mappedBy="entry")
//	private List<DailyLog> dailyLogs;
//
//	//bi-directional many-to-one association to Image
//	@OneToMany(mappedBy="entry")
//	private List<Image> images;

	public Entry() {
	}

	public int getEntId() {
		return this.entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public Date getEntDate() {
		return this.entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public String getEntText() {
		return this.entText;
	}

	public void setEntText(String entText) {
		this.entText = entText;
	}

	public String getEntTitle() {
		return this.entTitle;
	}

	public void setEntTitle(String entTitle) {
		this.entTitle = entTitle;
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
//		categoriesEntry.setEntry(this);
//
//		return categoriesEntry;
//	}
//
//	public CategoriesEntry removeCategoriesEntry(CategoriesEntry categoriesEntry) {
//		getCategoriesEntries().remove(categoriesEntry);
//		categoriesEntry.setEntry(null);
//
//		return categoriesEntry;
//	}
//
//	public List<Collaborator> getCollaborators() {
//		return this.collaborators;
//	}
//
//	public void setCollaborators(List<Collaborator> collaborators) {
//		this.collaborators = collaborators;
//	}
//
//	public Collaborator addCollaborator(Collaborator collaborator) {
//		getCollaborators().add(collaborator);
//		collaborator.setEntry(this);
//
//		return collaborator;
//	}
//
//	public Collaborator removeCollaborator(Collaborator collaborator) {
//		getCollaborators().remove(collaborator);
//		collaborator.setEntry(null);
//
//		return collaborator;
//	}

//	public List<DailyLog> getDailyLogs() {
//		return this.dailyLogs;
//	}
//
//	public void setDailyLogs(List<DailyLog> dailyLogs) {
//		this.dailyLogs = dailyLogs;
//	}
//
//	public DailyLog addDailyLog(DailyLog dailyLog) {
//		getDailyLogs().add(dailyLog);
//		dailyLog.setEntry(this);
//
//		return dailyLog;
//	}
//
//	public DailyLog removeDailyLog(DailyLog dailyLog) {
//		getDailyLogs().remove(dailyLog);
//		dailyLog.setEntry(null);
//
//		return dailyLog;
//	}

//	public List<Image> getImages() {
//		return this.images;
//	}
//
//	public void setImages(List<Image> images) {
//		this.images = images;
//	}
//
//	public Image addImage(Image image) {
//		getImages().add(image);
//		image.setEntry(this);
//
//		return image;
//	}
//
//	public Image removeImage(Image image) {
//		getImages().remove(image);
//		image.setEntry(null);
//
//		return image;
//	}

}