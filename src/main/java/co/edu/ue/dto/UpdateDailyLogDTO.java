package co.edu.ue.dto;


import java.util.Objects;

public class UpdateDailyLogDTO {

    private String entText;
    private String entTitle;
    private int idEmoLog;
    private int idEmoLogState;

    public UpdateDailyLogDTO() {
    }

    public UpdateDailyLogDTO(String entText, String entTitle, int idEmoLog, int idEmoLogState) {
        this.entText = entText;
        this.entTitle = entTitle;
        this.idEmoLog = idEmoLog;
        this.idEmoLogState = idEmoLogState;
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

    public int getIdEmoLog() {
        return idEmoLog;
    }

    public void setIdEmoLog(int idEmoLog) {
        this.idEmoLog = idEmoLog;
    }

    public int getIdEmoLogState() {
        return this.idEmoLogState;
    }

    public void setIdEmoLogState(int idEmoLogState) {
        this.idEmoLogState = idEmoLogState;
    }

    // Método toString
    @Override
    public String toString() {
        return "UpdateDailyLogDTO{" + "entText='" + entText + '\'' + ", entTitle='" + entTitle + '\'' + ", idEmoLog=" + idEmoLog + ", idEmoLogState=" + idEmoLogState + '}';
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDailyLogDTO that = (UpdateDailyLogDTO) o;
        return idEmoLog == that.idEmoLog && idEmoLogState == that.idEmoLogState &&

                Objects.equals(entText, that.entText) && Objects.equals(entTitle, that.entTitle);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(entText, entTitle, idEmoLog,idEmoLogState);
    }
}
