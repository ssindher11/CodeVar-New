package com.vit.codevar;

import com.google.gson.annotations.SerializedName;

public class Cookie {
    @SerializedName("CP")
    public String CP;

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }
}
