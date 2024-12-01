package co.edu.ue.dto;

import java.util.Date;

public class DailyLogFullInfo {
	
	private int useId;
	private int emoStaId;
	private String entText;
	private String entTitle;
	
	
	public DailyLogFullInfo(int useId, int emoStaId, String entText, String entTitle) {
		super();
		this.useId = useId;
		this.emoStaId = emoStaId;
		this.entText = entText;
		this.entTitle = entTitle;
	}
	
	
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public int getEmoStaId() {
		return emoStaId;
	}
	public void setEmoStaId(int emoStaId) {
		this.emoStaId = emoStaId;
	}
	public String getEntText() {
		return entText;
	}
	public void setEntText(String entText) {
		this.entText = entText;
	}
	public String getEntTitle() {
		return entTitle;
	}
	public void setEntTitle(String entTitle) {
		this.entTitle = entTitle;
	}
	
	
	
	
}
