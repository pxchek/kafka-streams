package com.example.trace;

import java.io.Serializable;
import java.util.StringJoiner;

public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    private int alertId;
    private String stageId;
    private String alertLevel;
    private String alertMessage;

    public Alert(int alertId, String stageId, String alertLevel, String alertMessage) {
        this.alertId = alertId;
        this.stageId = stageId;
        this.alertLevel = alertLevel;
        this.alertMessage = alertMessage;
    }

    public int getAlertId() {
        return alertId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    @Override public String toString() {
        return new StringJoiner(", ", Alert.class.getSimpleName() + "[", "]").add("alertId=" + alertId).add("stageId='" + stageId + "'")
                .add("alertLevel='" + alertLevel + "'").add("alertMessage='" + alertMessage + "'").toString();
    }
}