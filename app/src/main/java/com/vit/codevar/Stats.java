package com.vit.codevar;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("level")
    public String level;

    @SerializedName("intelligence")
    public String intelligence;

    @SerializedName("helpfulness")
    public String helpfulness;

    @SerializedName("activeness")
    public String activeness;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getHelpfulness() {
        return helpfulness;
    }

    public void setHelpfulness(String helpfulness) {
        this.helpfulness = helpfulness;
    }

    public String getActiveness() {
        return activeness;
    }

    public void setActiveness(String activeness) {
        this.activeness = activeness;
    }
}
