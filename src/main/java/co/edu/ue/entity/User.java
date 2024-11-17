package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


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

//	
//bi-directional many-to-one association to Collaborator
//	@OneToMany(mappedBy="user")
//	private List<Collaborator> collaborators;
//
//	//bi-directional many-to-one association to EmotionalLog
//	@OneToMany(mappedBy="user")
//	private List<EmotionalLog> emotionalLogs;
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
//		collaborator.setUser(this);
//
//		return collaborator;
//	}
//
//	public Collaborator removeCollaborator(Collaborator collaborator) {
//		getCollaborators().remove(collaborator);
//		collaborator.setUser(null);
//
//		return collaborator;
//	}
//
//	public List<EmotionalLog> getEmotionalLogs() {
//		return this.emotionalLogs;
//	}
//
//	public void setEmotionalLogs(List<EmotionalLog> emotionalLogs) {
//		this.emotionalLogs = emotionalLogs;
//	}
//
//	public EmotionalLog addEmotionalLog(EmotionalLog emotionalLog) {
//		getEmotionalLogs().add(emotionalLog);
//		emotionalLog.setUser(this);
//
//		return emotionalLog;
//	}
//
//	public EmotionalLog removeEmotionalLog(EmotionalLog emotionalLog) {
//		getEmotionalLogs().remove(emotionalLog);
//		emotionalLog.setUser(null);
//
//		return emotionalLog;
//	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}