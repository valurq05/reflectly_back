package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the collaborators database table.
 * 
 */
@Entity
@Table(name="collaborators")
@NamedQuery(name="Collaborator.findAll", query="SELECT c FROM Collaborator c")
public class Collaborator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="col_id")
	private int colId;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	@JoinColumn(name="ent_id")
	private Entry entry;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="use_id")
	private User user;

	public Collaborator() {
	}

	public int getColId() {
		return this.colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}