package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the emotional_states database table.
 * 
 */
@Entity
@Table(name="emotional_states")
@NamedQuery(name="EmotionalState.findAll", query="SELECT e FROM EmotionalState e")
public class EmotionalState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emo_sta_id")
	private int emoStaId;

	@Column(name="emo_sta_state")
	private String emoStaState;

//	//bi-directional many-to-one association to EmotionalLog
//	@OneToMany(mappedBy="emotionalState")
//	private List<EmotionalLog> emotionalLogs;

	public EmotionalState() {
	}

	public int getEmoStaId() {
		return this.emoStaId;
	}

	public void setEmoStaId(int emoStaId) {
		this.emoStaId = emoStaId;
	}

	public String getEmoStaState() {
		return this.emoStaState;
	}

	public void setEmoStaState(String emoStaState) {
		this.emoStaState = emoStaState;
	}

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
//		emotionalLog.setEmotionalState(this);
//
//		return emotionalLog;
//	}
//
//	public EmotionalLog removeEmotionalLog(EmotionalLog emotionalLog) {
//		getEmotionalLogs().remove(emotionalLog);
//		emotionalLog.setEmotionalState(null);
//
//		return emotionalLog;
//	}

}