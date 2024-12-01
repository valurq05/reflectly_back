package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the emotional_log database table.
 * 
 */
@Entity
@Table(name="emotional_log")
@NamedQuery(name="EmotionalLog.findAll", query="SELECT e FROM EmotionalLog e")
public class EmotionalLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emo_log_id")
	private int emoLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="emo_log_date")
	private Date emoLogDate;

//	//bi-directional many-to-one association to DailyLog
//	@OneToMany(mappedBy="emotionalLog")
//	private List<DailyLog> dailyLogs;

	//bi-directional many-to-one association to EmotionalState
	@ManyToOne
	@JoinColumn(name="emo_sta_id")
	private EmotionalState emotionalState;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="use_id")
	private User user;

	public EmotionalLog() {
	}

	public int getEmoLogId() {
		return this.emoLogId;
	}

	public void setEmoLogId(int emoLogId) {
		this.emoLogId = emoLogId;
	}

	public Date getEmoLogDate() {
		return this.emoLogDate;
	}

	public void setEmoLogDate(Date emoLogDate) {
		this.emoLogDate = emoLogDate;
	}

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
//		dailyLog.setEmotionalLog(this);
//
//		return dailyLog;
//	}
//
//	public DailyLog removeDailyLog(DailyLog dailyLog) {
//		getDailyLogs().remove(dailyLog);
//		dailyLog.setEmotionalLog(null);
//
//		return dailyLog;
//	}

	public EmotionalState getEmotionalState() {
		return this.emotionalState;
	}

	public void setEmotionalState(EmotionalState emotionalState) {
		this.emotionalState = emotionalState;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}