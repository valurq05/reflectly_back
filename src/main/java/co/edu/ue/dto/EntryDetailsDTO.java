package co.edu.ue.dto;

import java.time.LocalDate;
import java.util.Date;

public class EntryDetailsDTO {

    private Date dayLogDate;
    private int emoLogId;
    private Date emoLogDate;
    private String emoStaState;
    private Date entDate;
    private String entTitle;
    private String entText;
    private String perName;
    private String useMail;


  
    public EntryDetailsDTO(Date dayLogDate, int emoLogId, Date emoLogDate, String emoStaState, Date entDate,
			String entTitle, String entText, String perName, String useMail) {
		super();
		this.dayLogDate = dayLogDate;
		this.emoLogId = emoLogId;
		this.emoLogDate = emoLogDate;
		this.emoStaState = emoStaState;
		this.entDate = entDate;
		this.entTitle = entTitle;
		this.entText = entText;
		this.perName = perName;
		this.useMail = useMail;
	}



	public Date getDayLogDate() {
		return dayLogDate;
	}



	public void setDayLogDate(Date dayLogDate) {
		this.dayLogDate = dayLogDate;
	}



	public int getEmoLogId() {
		return emoLogId;
	}



	public void setEmoLogId(int emoLogId) {
		this.emoLogId = emoLogId;
	}



	public Date getEmoLogDate() {
		return emoLogDate;
	}



	public void setEmoLogDate(Date emoLogDate) {
		this.emoLogDate = emoLogDate;
	}



	public String getEmoStaState() {
		return emoStaState;
	}



	public void setEmoStaState(String emoStaState) {
		this.emoStaState = emoStaState;
	}



	public Date getEntDate() {
		return entDate;
	}



	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}



	public String getEntTitle() {
		return entTitle;
	}



	public void setEntTitle(String entTitle) {
		this.entTitle = entTitle;
	}



	public String getEntText() {
		return entText;
	}



	public void setEntText(String entText) {
		this.entText = entText;
	}



	public String getPerName() {
		return perName;
	}



	public void setPerName(String perName) {
		this.perName = perName;
	}



	public String getUseMail() {
		return useMail;
	}



	public void setUseMail(String useMail) {
		this.useMail = useMail;
	}


}
