package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the images database table.
 * 
 */
@Entity
@Table(name="images")
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="img_id")
	private int imgId;

	@Column(name="img_url")
	private String imgUrl;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	@JoinColumn(name="ent_id")
	private Entry entry;

	public Image() {
	}

	public int getImgId() {
		return this.imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}