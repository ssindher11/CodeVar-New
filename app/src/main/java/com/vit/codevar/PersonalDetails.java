package com.vit.codevar;

import com.google.gson.annotations.SerializedName;

public class PersonalDetails {
    @SerializedName("code")
    public int code;

    @SerializedName("userData")
    public UserData userData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
