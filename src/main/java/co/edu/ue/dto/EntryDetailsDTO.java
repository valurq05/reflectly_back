package co.edu.ue.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryDetailsDTO {

	private int entId;
    private Date dayLogDate;
    private int emoLogId;
    private Date emoLogDate;
    private String emoStaState;
    private Date entDate;
    private String entTitle;
    private String entText;
    private String perName;
    private String useMail;
    private List<String> catCategorie;
    
	public EntryDetailsDTO(int entId,Date dayLogDate, int emoLogId, Date emoLogDate, String emoStaState, Date entDate,
			String entTitle, String entText, String perName, String useMail, List<String> catCategorie) {
		super();
		this.entId = entId;
		this.dayLogDate = dayLogDate;
		this.emoLogId = emoLogId;
		this.emoLogDate = emoLogDate;
		this.emoStaState = emoStaState;
		this.entDate = entDate;
		this.entTitle = entTitle;
		this.entText = entText;
		this.perName = perName;
		this.useMail = useMail;
		this.catCategorie = catCategorie;
	}
	
	

	public EntryDetailsDTO(int entId, Date dayLogDate, int emoLogId, Date emoLogDate, String emoStaState, Date entDate,
			String entTitle, String entText, String perName, String useMail) {
		super();
		this.entId = entId;
		this.dayLogDate = dayLogDate;
		this.emoLogId = emoLogId;
		this.emoLogDate = emoLogDate;
		this.emoStaState = emoStaState;
		this.entDate = entDate;
		this.entTitle = entTitle;
		this.entText = entText;
		this.perName = perName;
		this.useMail = useMail;
		this.catCategorie = new ArrayList<>();
	}



	public int getEntId() {
		return entId;
	}


	public void setEntId(int entId) {
		this.entId = entId;
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
	public List<String> getCatCategorie() {
		return catCategorie;
	}
	public void setCatCategorie(List<String> catCategorie) {
		this.catCategorie = catCategorie;
	}

	 public void addCategory(String category) {
	        this.catCategorie.add(category);
	    }

	

}
