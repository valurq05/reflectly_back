package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the daily_log database table.
 * 
 */
@Entity
@Table(name="daily_log")
@NamedQuery(name="DailyLog.findAll", query="SELECT d FROM DailyLog d")
public class DailyLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="day_log_id")
	private int dayLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="day_log_date")
	private Date dayLogDate;

	//bi-directional many-to-one association to EmotionalLog
	@ManyToOne
	@JoinColumn(name="emo_log_id")
	private EmotionalLog emotionalLog;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	@JoinColumn(name="ent_id")
	private Entry entry;

	public DailyLog() {
	}

	public int getDayLogId() {
		return this.dayLogId;
	}

	public void setDayLogId(int dayLogId) {
		this.dayLogId = dayLogId;
	}

	public Date getDayLogDate() {
		return this.dayLogDate;
	}

	public void setDayLogDate(Date dayLogDate) {
		this.dayLogDate = dayLogDate;
	}

	public EmotionalLog getEmotionalLog() {
		return this.emotionalLog;
	}

	public void setEmotionalLog(EmotionalLog emotionalLog) {
		this.emotionalLog = emotionalLog;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}