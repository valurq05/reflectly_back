package co.edu.ue.dto;


import java.util.Objects;

public class UpdateDailyLogDTO {

    private String entText;
    private String entTitle;
    private int idEmoLog;
    private int idEmoState;

    public UpdateDailyLogDTO() {
    }

    public UpdateDailyLogDTO(String entText, String entTitle, int idEmoLog, int idEmoState) {
        this.entText = entText;
        this.entTitle = entTitle;
        this.idEmoLog = idEmoLog;
        this.idEmoState = idEmoState;
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

    public int getIdEmoState() {
        return this.idEmoState;
    }

    public void setIdEmoState(int idEmoState) {
        this.idEmoState = idEmoState;
    }

    // Método toString
    @Override
    public String toString() {
        return "UpdateDailyLogDTO{" + "entText='" + entText + '\'' + ", entTitle='" + entTitle + '\'' + ", idEmoLog=" + idEmoLog + ", idEmoState=" + idEmoState + '}';
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDailyLogDTO that = (UpdateDailyLogDTO) o;
        return idEmoLog == that.idEmoLog && idEmoState == that.idEmoState &&

                Objects.equals(entText, that.entText) && Objects.equals(entTitle, that.entTitle);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(entText, entTitle, idEmoLog,idEmoState);
    }
}
