package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the persons database table.
 * 
 */
@Entity
@Table(name="persons")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="per_id")
	private int perId;

	@Column(name="per_lastname")
	private String perLastname;

	@Column(name="per_name")
	private String perName;

	@Lob
	@Column(name="per_photo")
	private String perPhoto;
//
//	//bi-directional many-to-one association to User
//	@OneToMany(mappedBy="person")
//	private List<User> users;

	public Person() {
	}

	public int getPerId() {
		return this.perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

	public String getPerLastname() {
		return this.perLastname;
	}

	public void setPerLastname(String perLastname) {
		this.perLastname = perLastname;
	}

	public String getPerName() {
		return this.perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerPhoto() {
		return this.perPhoto;
	}

	public void setPerPhoto(String perPhoto) {
		this.perPhoto = perPhoto;
	}

//	public List<User> getUsers() {
//		return this.users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//
//	public User addUser(User user) {
//		getUsers().add(user);
//		user.setPerson(this);
//
//		return user;
//	}
//
//	public User removeUser(User user) {
//		getUsers().remove(user);
//		user.setPerson(null);
//
//		return user;
//	}

}