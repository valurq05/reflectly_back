package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="use_id")
	private int useId;

	@Column(name="use_mail")
	private String useMail;

	@Column(name="use_password")
	private String usePassword;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="per_id")
	private Person person;

	public User() {
	}

	public int getUseId() {
		return this.useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public String getUseMail() {
		return this.useMail;
	}

	public void setUseMail(String useMail) {
		this.useMail = useMail;
	}

	public String getUsePassword() {
		return this.usePassword;
	}

	public void setUsePassword(String usePassword) {
		this.usePassword = usePassword;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}